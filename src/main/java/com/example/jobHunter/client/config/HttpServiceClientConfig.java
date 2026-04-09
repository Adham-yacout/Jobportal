package com.example.jobHunter.client.config;

import com.example.jobHunter.client.service.ToDoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(group = "todos", types = {ToDoService.class})
//@ImportHttpServices(group = "posts", types = {PostService.class})
public class HttpServiceClientConfig {

    @Bean
    public RestClientHttpServiceGroupConfigurer groupConfigurer() {
        return groups -> {
            groups.filterByName("todos").forEachClient(
                    (group, restClientBuilder) -> {
                        restClientBuilder.baseUrl("http://jsonplaceholder.typicode.com/todos")
                                .requestInterceptor((request, body, execution) -> {
                                    //request.getHeaders().setBearerAuth("Bearer Token");
                                    // request.getHeaders().add("Header Name", "Header Value");
                                    return execution.execute(request, body);
                                });
                    }
            );
//
        };
    }
}
