package br.com.project.app.err.controllerAdv;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.project.app.err.BadRequesteexcption;

@ControllerAdvice
public class ExecptionErroControlerAdv {

    @ExceptionHandler(BadRequesteexcption.class)
    public ResponseEntity<String> handleBadRequest(BadRequesteexcption ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getLocalizedMessage());
    }
}
