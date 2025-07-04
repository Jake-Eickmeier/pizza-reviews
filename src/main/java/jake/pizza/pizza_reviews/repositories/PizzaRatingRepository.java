package jake.pizza.pizza_reviews.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import jake.pizza.pizza_reviews.models.PizzaReview;

@Repository
public interface PizzaRatingRepository {

    public List<PizzaReview> findAll();

    public PizzaReview findOne(String id);

    public PizzaReview save(PizzaReview pizzaOrder);
}