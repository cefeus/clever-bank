import dto.StatementDto;
import service.impl.PdfServiseImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        BigDecimal dec = BigDecimal.valueOf(1000);
        StatementDto dto = StatementDto.builder()
                .number("0000-0000-0000-0000")
                .bankName("Clever Банк")
                .balance(dec)
                .creation_date(new Timestamp(System.currentTimeMillis()))
                .owner("Василий Васильевич Ъуь")
                .build();
        PdfServiseImpl pdf = new PdfServiseImpl();
        pdf.formStatement(dto, LocalDate.parse("2023-05-05"), LocalDate.parse("2023-06-12"));
       /* val flywayConfig = new FlywayConfig();
        val appMenu = new AppMenu();
        flywayConfig.flywayTemplate().migrate();
        appMenu.start();*/
    }
}
