package br.com.cursoudemy.productapi.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidateException extends RuntimeException {
    public ValidateException(String message) {
        super(message);
    }
}
