import config.db.FlywayConfig;
import lombok.val;

public class Main {

    public static void main(String[] args) {
        val flywayConfig = new FlywayConfig();
        val appMenu = new AppMenu();
        flywayConfig.flywayTemplate().migrate();
        appMenu.start();
    }
}
