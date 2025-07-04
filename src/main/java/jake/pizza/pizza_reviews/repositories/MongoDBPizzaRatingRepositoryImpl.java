package jake.pizza.pizza_reviews.repositories;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import jakarta.annotation.PostConstruct;
import jake.pizza.pizza_reviews.models.PizzaReview;

@Repository
public class MongoDBPizzaRatingRepositoryImpl implements PizzaRatingRepository {

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();
    private final MongoClient client;
    private MongoCollection<PizzaReview> pizzaOrderCollection;

    public MongoDBPizzaRatingRepositoryImpl(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    @PostConstruct
    void init() {
        pizzaOrderCollection = client.getDatabase("test").getCollection("pizzaReviews", PizzaReview.class);
    }

    @Override
    public List<PizzaReview> findAll() {
        return pizzaOrderCollection.find().into(new ArrayList<>());
    }

    @Override
    public PizzaReview save(PizzaReview pizzaOrder) {
        pizzaOrderCollection.insertOne(pizzaOrder);
        return pizzaOrder;
    }

    @Override
    public PizzaReview findOne(String id) {
        return pizzaOrderCollection.find(eq("_id", new ObjectId(id))).first();
    }

}
