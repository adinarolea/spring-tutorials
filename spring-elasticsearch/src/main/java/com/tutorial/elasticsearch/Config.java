package com.tutorial.elasticsearch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

@Configuration
@EnableElasticsearchRepositories
public class Config {

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
    }
}
