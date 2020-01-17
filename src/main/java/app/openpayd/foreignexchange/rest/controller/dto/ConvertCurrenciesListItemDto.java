package app.openpayd.foreignexchange.rest.controller.dto;

import app.openpayd.foreignexchange.service.model.CurrencyConversion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvertCurrenciesListItemDto {
    private Long transactionId;
    private LocalDate createdAt;
    private String sourceCurrency;
    private String targetCurrency;
    private Double sourceAmount;
    private Double amountInTargetCurrency;

    public static ConvertCurrenciesListItemDto of(CurrencyConversion currencyConversion) {
        return ConvertCurrenciesListItemDto.builder()
                .transactionId(currencyConversion.getId())
                .createdAt(currencyConversion.getCreatedAt())
                .sourceCurrency(currencyConversion.getSourceCurrency())
                .targetCurrency(currencyConversion.getTargetCurrency())
                .sourceAmount(currencyConversion.getSourceAmount())
                .amountInTargetCurrency(currencyConversion.getAmountInTargetCurrency())
                .build();
    }
}
