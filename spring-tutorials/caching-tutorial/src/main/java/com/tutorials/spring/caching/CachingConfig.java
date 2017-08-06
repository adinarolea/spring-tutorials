package com.tutorials.spring.caching;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    public CaffeineCacheManager caffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheNames(Collections.singleton("api-cache"));
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder().maximumSize(5).expireAfterAccess(5, TimeUnit.MINUTES);
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }
}
