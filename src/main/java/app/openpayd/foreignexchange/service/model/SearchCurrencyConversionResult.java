package app.openpayd.foreignexchange.service.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchCurrencyConversionResult {

    int page;
    int size;
    Long totalSize;
    List<CurrencyConversion> currencyConversionList;
}
