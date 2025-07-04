package jake.pizza.pizza_reviews;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@Configuration
@EnableElasticsearchRepositories(basePackages = "jake.pizza.pizza_reviews.repositories")
@ComponentScan(basePackages = { "jake.pizza.pizza_reviews.service" })
public class ElasticConfiguration extends ElasticsearchConfiguration {

    @Value("${spring.elasticsearch.uris}")
    String connectionUri;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(connectionUri)
                .withConnectTimeout(10000) 
                .withSocketTimeout(60000)
                .build();
    }

}
