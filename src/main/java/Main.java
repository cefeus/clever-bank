import config.db.FlywayConfig;
import lombok.val;
import service.impl.PdfServiceImpl;

public class Main {
    //private static PdfServiceImpl service = new PdfServiceImpl();
    public static void main(String[] args) {
        val flywayConfig = new FlywayConfig();
        val appMenu = new AppMenu();
        flywayConfig.flywayTemplate().migrate();
        appMenu.start();
        // service.formPdf("fds ôû", "check/121.pdf");
    }
}
