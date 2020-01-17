package app.openpayd.foreignexchange.rest.controller.dto;

import app.openpayd.foreignexchange.service.model.SearchCurrencyConversionResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListCurrencyConversionsResponse {

    private int page;
    private int size;
    private Long totalSize;
    private List<ConvertCurrenciesListItemDto> convertCurrenciesList;

    public static ListCurrencyConversionsResponse of(SearchCurrencyConversionResult searchCurrencyConversionResult) {
        return ListCurrencyConversionsResponse.builder()
                .page(searchCurrencyConversionResult.getPage())
                .size(searchCurrencyConversionResult.getSize())
                .totalSize(searchCurrencyConversionResult.getTotalSize())
                .convertCurrenciesList(searchCurrencyConversionResult.getCurrencyConversionList().stream()
                        .map(ConvertCurrenciesListItemDto::of).collect(Collectors.toList()))
                .build();
    }
}
