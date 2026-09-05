import dto.User;
import enums.UserCategory;
import service.LimiterFactory;
import service.RateLimiterService;

import static enums.UserCategory.FREE;


void main() throws InterruptedException {
    limiterSetUpAndCall();

//    checkConcurrency();

}

private void limiterSetUpAndCall() {
    //Create Users
    User user1 = new User("1","Saket", FREE);
    User user2 = new User("2","Singh", UserCategory.PAID);
    User user3 = new User("3","NewUser",UserCategory.PREMIUM);

    // Call 15 times , for User 1
    System.out.println("============== USER 1 Requests ==============");
    for(int i=1;i<=15;i++) {
        if(callLimiter(user1)) {
            System.out.println("Request " + i + " ALLOWED");
        } else {
            System.out.println("Request " + i + " DENIED");
        }
    }
    System.out.println("============== USER 2 Requests ==============");
    //Call 15 times , for User 2
    for(int i=1;i<=15;i++) {
        if(callLimiter(user2)) {
            System.out.println("Request " + i + " ALLOWED");
        } else {
            System.out.println("Request " + i + " DENIED");
        }
    }

    System.out.println("============== USER 3 Requests ==============");

    //Call 15 times , for User 3
    for(int i=1;i<=15;i++) {
        if(callLimiter(user3)) {
            System.out.println("Request " + i + " ALLOWED");
        } else {
            System.out.println("Request " + i + " DENIED");
        }
    }
}

private boolean callLimiter(User user) {
    RateLimiterService limiterService = LimiterFactory.getLimiterService(user.getCategory());
    return limiterService.isAllowRequest(user);
}

static void checkConcurrency() throws InterruptedException {
    User freeUser1 = new User("user1","ThreadUser", FREE);

    int threads = 20; // simulate 20 concurrent requests
    ExecutorService executor = Executors.newFixedThreadPool(threads);

    CyclicBarrier barrier = new CyclicBarrier(threads);
    CountDownLatch latch = new CountDownLatch(threads);
    RateLimiterService limiterService = LimiterFactory.getLimiterService(freeUser1.getCategory());
    for (int i = 1; i <= threads; i++) {
        final int reqNum = i;
        executor.submit(() -> {
            try {
                // all threads wait here until barrier is full
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

            boolean allowed = limiterService.isAllowRequest(freeUser1);
            System.out.println(Thread.currentThread().getName() +
                    " | Request " + reqNum + " for FreeUser1: " + (allowed ? "ALLOWED" : "BLOCKED"));

            latch.countDown();
        });
    }

    latch.await(); // wait for all threads to finish
    executor.shutdown();
}
