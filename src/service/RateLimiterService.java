package service;

import dto.User;

public interface RateLimiterService {
    boolean isAllowRequest(User user);
}
