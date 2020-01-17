package app.openpayd.foreignexchange.rest.controller.dto;

import app.openpayd.foreignexchange.service.model.SearchCurrencyConversion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversionListRequest {

    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate createdAt;

    @Min(0)
    private Integer page = 0;

    @Min(0)
    @Max(100)
    private Integer size = 10;

    public SearchCurrencyConversion toModel() {
        return SearchCurrencyConversion.builder()
                .createdAt(createdAt)
                .id(id)
                .page(page)
                .size(size)
                .build();
    }
}
