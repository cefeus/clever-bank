package dto;

import lombok.*;
import model.StatementType;


import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class StatementDto {
    private String accNumber;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private StatementType type;
}