package jake.pizza.pizza_reviews.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import jake.pizza.pizza_reviews.models.PizzaReview;

@Repository
public interface PizzaReviewRepository extends ElasticsearchRepository<PizzaReview, String> {

    @Override
    public List<PizzaReview> findAll();

    public List<PizzaReview> searchByPizzaName(String keyword);

    public List<PizzaReview> searchByComment(String keyword);

}
