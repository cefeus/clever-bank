package service;

import lombok.NoArgsConstructor;
import repository.AccountRepo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor
public class AccrualScheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final AccountRepo repo = new AccountRepo();

    public void setSchedule() {
        scheduler.scheduleAtFixedRate(this::accrueInterests, 0, 30, TimeUnit.SECONDS);
    }

    private void accrueInterests() {
        CompletableFuture.runAsync(repo::accrueInterest, scheduler);
    }

}
