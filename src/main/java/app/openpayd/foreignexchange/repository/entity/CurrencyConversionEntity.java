package app.openpayd.foreignexchange.repository.entity;

import app.openpayd.foreignexchange.service.model.CurrencyConversion;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "currency_conversion")
@Table(name = "currency_conversion")
@EntityListeners(AuditingEntityListener.class)
public class CurrencyConversionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_conversion_seq_generator")
    @SequenceGenerator(name = "currency_conversion_seq_generator", sequenceName = "currency_conversion_id_seq", allocationSize = 1)
    @Column(updatable = false, nullable = false, columnDefinition = "bigserial")
    private Long id;

    @CreatedDate
    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private String sourceCurrency;

    @Column(nullable = false)
    private String targetCurrency;

    @Column(nullable = false)
    private Double sourceAmount;

    @Column(nullable = false)
    private Double amountInTargetCurrency;

    public CurrencyConversion toModel() {
        return CurrencyConversion.builder()
                .id(id)
                .createdAt(createdAt)
                .sourceCurrency(sourceCurrency)
                .targetCurrency(targetCurrency)
                .sourceAmount(sourceAmount)
                .amountInTargetCurrency(amountInTargetCurrency)
                .build();
    }
}
