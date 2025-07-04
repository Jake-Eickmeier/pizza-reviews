## pizza-reviews

This service handles the reviews submitted by paying customers in the pizza webapp. When a review for a given pizza is POSTed to this service, it does two main things:
1. Indexes the review in an elasticsearch cluster for storage and so that other customers can search for keywords such as "delicious" and find matching reviews. Obviously this is not very practical, but this is mostly just for demonstration purposes of elasticsearch.
2. Updates the "star rating" in the pizza MongoDB collection by re-calculating the average star rating including the new rating from this review.


### How to run
Before running, make sure the required MongoDB collection and kafka topic is available. You can accomplish this by running `docker-compose up -d` in the root directory of `pizza-project`, which will run all dependencies for all services, or run the same command in the root of this project which will run only the dependencies required for this service.
You can run this service locally using your IDE or the command `mvn spring-boot:run`, which will make the service available on port 8080. Swagger documentation is available [here](http://localhost:8080/swagger-ui/index.html).