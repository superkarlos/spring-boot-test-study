package br.com.project.app.err.controllerAdv;

import br.com.project.app.err.MessagerError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExecptionErroControlerAdv {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessagerError> handleValidationErrors(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(err -> err.getDefaultMessage())
                .orElse("Erro de validação");

        MessagerError msg = new MessagerError(HttpStatus.BAD_REQUEST, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }

    // (opcional) tratamento genérico
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessagerError> handleGeneric(Exception ex) {
        MessagerError msg = new MessagerError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
    }
}
