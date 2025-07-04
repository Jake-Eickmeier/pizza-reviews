package jake.pizza.pizza_reviews.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.search.Hit;

import jake.pizza.pizza_reviews.dtos.PizzaReviewDTO;
import jake.pizza.pizza_reviews.models.PizzaReview;
import jake.pizza.pizza_reviews.services.PizzaReviewService;



@RestController
public class ReviewController {

    @Autowired
    PizzaReviewService pizzaReviewService;

    ReviewController() {
    };

    @GetMapping("/hello")
    public String getHello() {
        return new String("Hello ");
    }

    @GetMapping("/test/mongoDb")
    public List<PizzaReviewDTO> getAllPizzaReviews() {
        return pizzaReviewService.findAll();
    }

    @PostMapping("/review")
    public ResponseEntity<PizzaReviewDTO> postPizzaReview(@RequestBody PizzaReviewDTO pizzaReviewDTO) {
        pizzaReviewService.savePizzaReview(pizzaReviewDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    
    @GetMapping("/reviews/{pizzaName}")
    public ResponseEntity<List<PizzaReviewDTO>> getPizzaReviews(@PathVariable String pizzaName) {
        List<PizzaReviewDTO> pizzaReviewDTOs = pizzaReviewService.findReviewsByPizzaName(pizzaName);
        ResponseEntity<List<PizzaReviewDTO>> response = new ResponseEntity<List<PizzaReviewDTO>>(pizzaReviewDTOs, HttpStatus.OK);
        return response;
    }
    
    @GetMapping("/reviews/search/{keyword}")
    public ResponseEntity<List<PizzaReviewDTO>> searchPizzaReviewDTOsByKeyword(@PathVariable String keyword) throws ElasticsearchException, IOException {
        List<PizzaReviewDTO> pizzaReviewDTOHits = pizzaReviewService.searchReviewsByKeyword(keyword);
        ResponseEntity<List<PizzaReviewDTO>> response = new ResponseEntity<>(pizzaReviewDTOHits, HttpStatus.OK);
        return response;
    }
    
    // TODO: More options for FE e.g.:
    // TODO: Endpoint to get all reviews by keyword search/pizza tag filter
    

}
