package org.alexey.ITBotcamp.exception;


import lombok.extern.log4j.Log4j2;
import org.alexey.ITBotcamp.exception.body.ErrorField;
import org.alexey.ITBotcamp.exception.body.ErrorResponse;
import org.alexey.ITBotcamp.exception.body.StructuredErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@ResponseBody
@RestControllerAdvice
public class ExceptionHandlerResolver {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorResponse handleValidationException(ValidationException exception) {
        log.error(exception.getMessage());
        return new ErrorResponse("error",
                exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageConversionException.class)
    public ErrorResponse handleNotReadableException(HttpMessageConversionException exception) {
        log.error(exception.getMessage());
        return new ErrorResponse("error",
                exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public StructuredErrorResponse handleValidation(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        return new StructuredErrorResponse(
                "structured_error",
                exception.getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map(this::convert)
                        .toList()
        );
    }

    private ErrorField convert(ObjectError objectError) {
        return new ErrorField(
                ((FieldError) objectError).getField(),
                objectError.getDefaultMessage()
        );
    }
}
