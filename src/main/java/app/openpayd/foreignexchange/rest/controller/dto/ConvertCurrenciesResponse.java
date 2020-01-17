package app.openpayd.foreignexchange.rest.controller.dto;

import app.openpayd.foreignexchange.service.model.CurrencyConversion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvertCurrenciesResponse {
    private Double amountInTargetCurrency;
    private Long transactionId;

    public static ConvertCurrenciesResponse of(CurrencyConversion currencyConversion) {
        return ConvertCurrenciesResponse.builder()
                .transactionId(currencyConversion.getId())
                .amountInTargetCurrency(currencyConversion.getAmountInTargetCurrency())
                .build();
    }
}
