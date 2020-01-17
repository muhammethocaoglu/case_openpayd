package app.openpayd.foreignexchange.service.model;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CurrencyConversion {

    private Long id;
    private LocalDate createdAt;
    private String sourceCurrency;
    private String targetCurrency;
    private Double sourceAmount;
    private Double amountInTargetCurrency;
}
