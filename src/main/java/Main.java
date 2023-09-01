import config.db.FlywayConfig;
import lombok.val;
import service.AccrualScheduler;

public class Main {
    public static void main(String[] args) {
        val flywayConfig = new FlywayConfig();
        val appMenu = new AppMenu();
        val scheduler = new AccrualScheduler();
        flywayConfig.flywayTemplate().migrate();
        scheduler.setSchedule();
        appMenu.start();
    }
}
