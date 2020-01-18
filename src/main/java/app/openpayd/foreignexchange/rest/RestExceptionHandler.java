package app.openpayd.foreignexchange.rest;

import app.openpayd.foreignexchange.exception.ForeignExchangeBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    private final MessageSource messageSource;

    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception exception, Locale locale) {
        log.error("An error occurred! Details: ", exception);
        return createErrorResponseFromMessageSource("foreignexchange.api.system.error.occurred", locale);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleRequestPropertyBindingError(BindException bindException, Locale locale) {
        log.error("Request property binding error occurred", bindException);
        return createErrorResponseFromMessageSource("foreignexchange.api.request.parameter.binding.error", locale);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException,
                                                           Locale locale) {
        log.error("Method argument not valid exception occurred", methodArgumentNotValidException);
        return createErrorResponseFromMessageSource("foreignexchange.api.request.method.argument.not.valid", locale);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException missingServletRequestParameterException,
                                                           Locale locale) {
        log.error("Missing servlet request parameter exception occurred", missingServletRequestParameterException);

        return createErrorResponseFromMessageSource("foreignexchange.api.missing.servlet.request.parameter", locale);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException,
                                                                   Locale locale) {
        log.error("MethodArgumentTypeMismatchException occurred", methodArgumentTypeMismatchException);
        return createErrorResponseFromMessageSource("foreignexchange.api.request.parameter.type.mismatch", locale,
                methodArgumentTypeMismatchException.getName());
    }

    @ExceptionHandler(ForeignExchangeBusinessException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleBonusApiBusinessException(ForeignExchangeBusinessException foreignExchangeBusinessException, Locale locale) {
        log.error("Business exception occurred", foreignExchangeBusinessException);
        return createErrorResponseFromMessageSource(foreignExchangeBusinessException.getKey(), locale);
    }

    private ErrorResponse createErrorResponseFromMessageSource(String key, Locale locale, String... args) {
        List<String> messageList = retrieveLocalizationMessage(key, locale, args);
        return new ErrorResponse(messageList.get(0), messageList.get(1));
    }

    private List<String> retrieveLocalizationMessage(String key, Locale locale, String... args) {
        String message = messageSource.getMessage(key, args, locale);
        return Pattern.compile(";").splitAsStream(message).collect(Collectors.toList());
    }
}
