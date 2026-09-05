package service;

import dto.User;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedWindowLimiter implements RateLimiterService{
    private final int maxReqAllowed;
    private final int windowTimeSeconds;

    public static class Window {
        long id;
        int count;

        Window(long winId ,int winCnt) {
            id = winId;
            count = winCnt;
        }
    }

    public FixedWindowLimiter(int maxReqAllowed, int windowTimeSeconds) {
        this.maxReqAllowed = maxReqAllowed;
        this.windowTimeSeconds = windowTimeSeconds;
    }

  private final ConcurrentHashMap<String, Window> userVReqCntMap =
      new ConcurrentHashMap<>(); // store User V Window(Id + count)

    @Override
    public boolean isAllowRequest(User user) {
        long windowId = System.currentTimeMillis() / 1000 / windowTimeSeconds;
        AtomicBoolean allowed = new AtomicBoolean(false);

        userVReqCntMap.compute(user.getName(), (name , window) -> {
            if(window == null || window.id != windowId) {
                allowed.set(true);
                return new Window(windowId,1);
            }
            if(window.count < maxReqAllowed) {
                allowed.set(true);
                window.count++;
            }
            return window;
        });
        return allowed.get();
    }
}
