package jake.pizza.pizza_reviews.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "pizza-reviews")
public class PizzaReview {

    @Id
    private String id;

    private String pizzaName;

    private String userName;

    private String reviewDate;

    private int stars;

    private String comment;

    public PizzaReview(String id, String pizzaName, String userName, String reviewDate,
            int stars, String comment) {
        this.id = id;
        this.pizzaName = pizzaName;
        this.userName = userName;
        this.reviewDate = reviewDate;
        this.stars = stars;
        this.comment = comment;
    }

    public PizzaReview() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
