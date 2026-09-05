package service;

import dto.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class TokenBucketLimiter implements RateLimiterService{

    private final int maxReqAllowed;
    private final int windowTimeSeconds;

    public TokenBucketLimiter(int maxReqAllowed, int windowTimeSeconds) {
        this.maxReqAllowed = maxReqAllowed;
        this.windowTimeSeconds = windowTimeSeconds;
    }
    private final Map<String, Integer> tokens = new ConcurrentHashMap<>();
    private final Map<String, Long> lastRefillTime = new ConcurrentHashMap<>();

    @Override
    public boolean isAllowRequest(User user) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        long now = System.currentTimeMillis();

        tokens.compute(user.getName(), (name , token) -> {
            int currentTokens = refillTokens(user.getName(), now);
            if(currentTokens > 0 ) {
                allowed.set(true);
                return currentTokens - 1;
            }
            return currentTokens;
        });
        return allowed.get();
    }

    private int refillTokens(String userName , long now) {
        double refillRate = (double) windowTimeSeconds / maxReqAllowed;
        lastRefillTime.putIfAbsent(userName, now);

        long lastRefill = lastRefillTime.get(userName);
        long elapsedSeconds = (now - lastRefill) / 1000;
        int refillTokens = (int) (elapsedSeconds / refillRate);
        int currentTokens = tokens.getOrDefault(userName, maxReqAllowed);
        currentTokens = Math.min(maxReqAllowed, currentTokens + refillTokens);

        if (refillTokens > 0) lastRefillTime.put(userName, now);
        return currentTokens;
    }
}
