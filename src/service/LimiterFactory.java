package service;

import enums.UserCategory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LimiterFactory {

    private static final Map<UserCategory, RateLimiterService> CACHE = new ConcurrentHashMap<>();

    public static RateLimiterService getLimiterService(UserCategory category) {
        return CACHE.computeIfAbsent(category,LimiterFactory::createLimiterService);
    }

    private static RateLimiterService createLimiterService(UserCategory category) {
        return switch (category) {
            case PAID -> new SlidingWindowLimiter(10,5);
            case FREE -> new FixedWindowLimiter(10,5);
            case PREMIUM -> new TokenBucketLimiter(10,5);
            default -> throw new IllegalArgumentException("Invalid Category");
        };
    }
}
