package service;

import dto.User;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class SlidingWindowLimiter implements RateLimiterService{
    private final int maxReqAllowed;
    private final int windowTimeSeconds;

    public SlidingWindowLimiter(int maxReqAllowed, int windowTimeSeconds) {
        this.maxReqAllowed = maxReqAllowed;
        this.windowTimeSeconds = windowTimeSeconds;
    }

    private final Map<String, Queue<Long>> requestLog = new ConcurrentHashMap<>();

    @Override
    public boolean isAllowRequest(User user) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        long now = System.currentTimeMillis() / 1000;

        requestLog.compute(user.getName(), (name , log) -> {
            if (log == null) log = new ArrayDeque<>();

            while(!log.isEmpty() && (now - log.peek()) > windowTimeSeconds) {
                log.poll();
            }

            if (log.size() < maxReqAllowed) {
                log.add(now);
                allowed.set(true);
            }
            return log;
        });
        return allowed.get();
    }
}
