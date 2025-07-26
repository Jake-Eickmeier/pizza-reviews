package jake.pizza.pizza_reviews.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.RefreshPolicy;
import org.springframework.stereotype.Repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;

import jake.pizza.pizza_reviews.models.PizzaReview;

@Repository
public class ElasticPizzaReviewRepositoryImpl implements PizzaReviewRepository {

    private final ElasticsearchClient elasticsearchClient;

    public ElasticPizzaReviewRepositoryImpl(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    @Override
    public <S extends PizzaReview> S save(S entity) {
        try {
            IndexResponse indexResponse = elasticsearchClient.index(i -> i
                .index("pizza-reviews")
                .id(entity.getId())
                .document(entity));
            return entity;
        } catch (Exception e) {
            // TODO: better error handling
            return null;
        }

    }

    @Override
    public List<PizzaReview> findAll() {
        try {
            SearchResponse<PizzaReview> response = elasticsearchClient.search(s -> s
                .index("pizza-reviews")
                .query(MatchAllQuery.of(m -> m)._toQuery()),
                PizzaReview.class);
            return response.hits().hits()
                .stream()
                .map(pizzaReviewHit -> pizzaReviewHit.source())
                .collect(Collectors.toList());
            } catch (Exception e) {
                // TODO: better error handling
                return null;
            }
        }

    @Override
    public List<PizzaReview> searchByKeyword(String keyword, String fieldName) {
        try {
            SearchResponse<PizzaReview> elasticResponse = elasticsearchClient.search(s -> s
            .index("pizza-reviews")
            .query(q -> q
                .match(t -> t
                    .field("comment")
                    .query(fieldName)
                )
            ),
            PizzaReview.class
        );

        return elasticResponse.hits().hits()
            .stream()
            .map(pizzaReviewHit -> pizzaReviewHit.source())
            .collect(Collectors.toList());
        } catch (Exception e) {
            // TODO: better error handling
            return null;
        }
    }

    @Override
    public Page<PizzaReview> searchSimilar(PizzaReview entity, String[] fields, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchSimilar'");
    }

    @Override
    public Iterable<PizzaReview> findAll(Sort sort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Page<PizzaReview> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public <S extends PizzaReview> Iterable<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
    }

    @Override
    public Optional<PizzaReview> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }

    @Override
    public Iterable<PizzaReview> findAllById(Iterable<String> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public void delete(PizzaReview entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllById'");
    }

    @Override
    public void deleteAll(Iterable<? extends PizzaReview> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public <S extends PizzaReview> S save(S entity, RefreshPolicy refreshPolicy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public <S extends PizzaReview> Iterable<S> saveAll(Iterable<S> entities, RefreshPolicy refreshPolicy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
    }

    @Override
    public void deleteById(String id, RefreshPolicy refreshPolicy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public void delete(PizzaReview entity, RefreshPolicy refreshPolicy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids, RefreshPolicy refreshPolicy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllById'");
    }

    @Override
    public void deleteAll(Iterable<? extends PizzaReview> entities, RefreshPolicy refreshPolicy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public void deleteAll(RefreshPolicy refreshPolicy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

}
