package br.com.cotiinformatica.api_usuarios.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/*
    Classe de tratamento de exceções global para erros lançados
    pela API do tipo ValidationException do Spring Bean Validation
    O tratamento deverá retornar um array de strings com todas
    as mensagens de erro de validação geradas pela biblioteca
 */
//spec driven development(SSD)
@ControllerAdvice
public class ValidationExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationException(MethodArgumentNotValidException e) {

        return e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();
    }
}
