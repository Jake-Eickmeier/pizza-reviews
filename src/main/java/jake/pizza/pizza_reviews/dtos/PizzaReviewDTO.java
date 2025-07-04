package jake.pizza.pizza_reviews.dtos;

import org.bson.types.ObjectId;

import jake.pizza.pizza_reviews.models.PizzaReview;

public record PizzaReviewDTO(
        String id,
        String pizzaName,
        String userName,
        // TODO: Accept null reviewdate and populate on creation
        // Only date itself e.g. yyyy-mm-dd should be required for this type of field
        String reviewDate,
        int stars,
        String comment) {

    public PizzaReviewDTO(PizzaReview pizzaReview) {
        this(pizzaReview.getId(), pizzaReview.getPizzaName(), pizzaReview.getUserName(),
                pizzaReview.getReviewDate(), pizzaReview.getStars(), pizzaReview.getComment());
    }

    public PizzaReview toPizzaReview() {
        ObjectId objectId = id == null ? new ObjectId() : new ObjectId(id);
        return new PizzaReview(objectId.toHexString(), pizzaName, userName, reviewDate, stars, comment);
    }

}
