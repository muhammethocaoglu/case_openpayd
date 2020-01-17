package app.openpayd.foreignexchange.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class SearchCurrencyConversion {

    @Builder.Default
    int page = 0;

    @Builder.Default
    int size = 10;

    private LocalDate createdAt;
    private Long id;
}
