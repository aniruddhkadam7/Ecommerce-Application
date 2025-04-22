package com.example.hypercart.Services;

import com.example.hypercart.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisProductService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate; // Use RedisTemplate<String, Object>

    private static final String PRODUCT_CACHE_KEY = "products:all";

    public void cacheProducts(List<Product> products) {
        redisTemplate.opsForValue().set(PRODUCT_CACHE_KEY, products, 1, TimeUnit.HOURS);
    }

    public List<Product> getCachedProducts() {
        return (List<Product>) redisTemplate.opsForValue().get(PRODUCT_CACHE_KEY);
    }

    public void invalidateCache() {
        redisTemplate.delete(PRODUCT_CACHE_KEY);
    }
}