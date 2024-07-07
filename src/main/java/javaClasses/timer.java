import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class timer {
    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> scheduledFuture;
    private int seconds;
    private boolean isPaused;

    public timer(int seconds) {
        this.seconds = seconds;
        this.isPaused = false;
        scheduler = Executors.newScheduledThreadPool(1);
    }

    public void start() {
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            scheduledFuture = scheduler.scheduleAtFixedRate(() -> {
                if (!isPaused) {
                    if (seconds > 0) {
                        seconds--;
                        System.out.println("Time left: " + seconds);
                    } else {
                        stop();
                        System.out.println("Time's up!");
                    }
                }
            }, 0, 1, TimeUnit.SECONDS);
        }
    }
    public int getTime(){
        return seconds;
    }
    public void pause() {
        isPaused = true;
    }

    public void resume() {
        isPaused = false;
    }

    public void stop() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            scheduler.shutdown();
        }
    }
}
