import config.db.FlywayConfig;
import lombok.val;
import utils.AccrualScheduler;

/**
 * главный класс,
 * который запускает наше приложение
 * @author cefe!s
 */
public class Main {

    public static void main(String[] args) {
        val flywayConfig = new FlywayConfig();
        val scheduler = new AccrualScheduler();
        val appMenu = new AppMenu();
        flywayConfig.flywayTemplate().migrate();
        scheduler.setSchedule();
        appMenu.start();
    }
}
