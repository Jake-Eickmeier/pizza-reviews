package jake.pizza.pizza_reviews.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import jake.pizza.pizza_reviews.dtos.PizzaReviewDTO;
import jake.pizza.pizza_reviews.repositories.PizzaRatingRepository;
import jake.pizza.pizza_reviews.repositories.PizzaReviewRepository;

@Service
public class PizzaReviewServiceImpl implements PizzaReviewService {

    private final PizzaRatingRepository pizzaRatingRepository;

    private final PizzaReviewRepository pizzaReviewRepository;

    public PizzaReviewServiceImpl(PizzaRatingRepository pizzaRatingRepository, PizzaReviewRepository pizzaReviewRepository, ElasticsearchClient elasticsearchClient) {
        this.pizzaRatingRepository = pizzaRatingRepository;
        this.pizzaReviewRepository = pizzaReviewRepository;
    }

    @Override
    public List<PizzaReviewDTO> findAll() {
        return pizzaReviewRepository.findAll()
            .stream()
            .map(pizzaReview -> new PizzaReviewDTO(pizzaReview))
            .collect(Collectors.toList());
    }

    @Override
    public void savePizzaReview(PizzaReviewDTO pizzaReviewDTO) {
        pizzaReviewRepository.save(pizzaReviewDTO.toPizzaReview());
        // TODO: Also extract the pizza RATING (just stars) and save to the pizza ratings mongodb
    }

    @Override
    public List<PizzaReviewDTO> findReviewsByPizzaName(String pizzaName) {
        return pizzaReviewRepository.searchByPizzaName(pizzaName)
            .stream()
            .map(pizzaReview -> new PizzaReviewDTO(pizzaReview))
            .collect(Collectors.toList());
    }

    @Override
    public List<PizzaReviewDTO> searchReviewsByKeyword(String keyword) throws ElasticsearchException, IOException {
        return pizzaReviewRepository.searchByComment(keyword)
            .stream()
            .map(pizzaReview -> new PizzaReviewDTO(pizzaReview))
            .collect(Collectors.toList());

    }

}
