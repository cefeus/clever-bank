package controller;

import dto.StatementDto;
import exception.CustomDateTimeParseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.StatementType;
import service.StatementService;
import service.impl.StatementServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@WebServlet("/statements/money-flow")
public class MoneyFlowController extends HttpServlet {
    private StatementService statementService = new StatementServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var statementDto = getStatementDtoFromRequest(req);
        statementService.createTransactionStatement(statementDto);
        System.out.println("Success");
    }

    private StatementDto getStatementDtoFromRequest(HttpServletRequest req) {
        var accNumber = req.getParameter("accNumber");
        var dateFrom = parseDate(req.getParameter("dateFrom"))
                .orElseThrow(() -> new CustomDateTimeParseException("Вы ввели неверный параметр"));
        var dateTo = parseDate(req.getParameter("dateTo"))
                .orElseThrow(() -> new CustomDateTimeParseException("Вы ввели неверный параметр"));
        return StatementDto.builder()
                .accNumber(accNumber)
                .dateFrom(dateFrom)
                .dateTo(dateTo)
                .type(StatementType.MONEY_FLOW)
                .build();
    }

    private Optional<LocalDate> parseDate(String date) {
        LocalDate parse;
        try {
            parse = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
        return Optional.of(parse);
    }
}
