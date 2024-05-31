package br.com.medvoll.api.models;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(ErrorRuntime.class)
    public ErrorClass Error404(Exception e){
        return new ErrorClass(LocalDateTime.now(),404,"error",e.getMessage(),"");
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorClass errorRuntime(Exception e){
        return new ErrorClass(LocalDateTime.now(),404,"error",e.getMessage(),"");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorValidacao(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(e.getFieldErrors().stream().map(fieldErros::new).toList());
    }

    private record fieldErros(String campo, String mensagem){
        private fieldErros(FieldError fieldError) {
          this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

}
