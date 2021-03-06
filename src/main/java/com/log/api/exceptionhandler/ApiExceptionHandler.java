package com.log.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.log.domain.exception.EntidadeNaoEncontradaException;
import com.log.domain.exception.NegocioException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        var problema = new Problema();
        List<Problema.Campo> campos = new ArrayList<>();

        for (ObjectError error : ex.getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            campos.add(new Problema.Campo(nome, mensagem));
        }

        problema.setCampos(campos);
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo("Um ou mais campos estão inválidos!");
        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
        var problema = new Problema();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo(ex.getMessage());
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(NegocioException ex, WebRequest request) {
        var problema = new Problema();
        HttpStatus status = HttpStatus.NOT_FOUND;
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo(ex.getMessage());
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }
}
