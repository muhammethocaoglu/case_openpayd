package app.openpayd.foreignexchange.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForeignExchangeBusinessException extends RuntimeException {
    private String key;
    private String[] args;

    public ForeignExchangeBusinessException(String key) {
        super(key);
        this.key = key;
    }

    public ForeignExchangeBusinessException(String key, String... args) {
        super(key);
        this.key = key;
        this.args = args;
    }
}
