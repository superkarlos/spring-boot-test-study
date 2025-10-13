package br.com.project.app.err;

import org.springframework.http.HttpStatus;

public record MessagerError(HttpStatus httpStatus, String messeger) {
    
}
