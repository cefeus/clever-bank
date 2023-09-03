package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import model.StatementType;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatementDto {
    private String accNumber;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private StatementType type;
}
