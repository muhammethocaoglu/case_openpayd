package app.openpayd.foreignexchange.service.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CreateCurrencyConversion {

    private Double sourceAmount;
    private String sourceCurrency;
    private String targetCurrency;
}
