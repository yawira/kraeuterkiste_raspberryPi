package aw.kraeuterkiste_raspberry.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    // RestTemplateBuilder is used for dynamic configuration of RestTemplate through application.properties

    @Value("${backend.url}")
    private String backendURL;

    @Bean("backendRestTemplate")
    public RestTemplate backendRestTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return backendRestTemplateBuilder(builder);
    }

    @Autowired
    public RestTemplate backendRestTemplateBuilder(RestTemplateBuilder builder) {
        return builder
                .rootUri(backendURL)
                .build();
    }
}
