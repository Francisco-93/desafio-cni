package br.com.francisco.prod.recursos.handler;

import br.com.francisco.prod.exception.ObjetoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = ObjetoNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> objectNotFound(ObjetoNaoEncontradoException ex, HttpServletRequest request){
        ErroPadrao error = new ErroPadrao(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
