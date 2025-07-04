package jake.pizza.pizza_reviews.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import jake.pizza.pizza_reviews.models.PizzaReview;

@Repository
public interface PizzaReviewRepository extends ElasticsearchRepository<PizzaReview, String> {

    public Page<PizzaReview> findByPizzaName(String pizzaName, Pageable pageable);

}
