package fr.m2i.apifilrougecrm.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CrmResponseEntity {

    public static ResponseEntity<Object> build(String errorMessage, int status, String path, HttpStatus httpStatus) {
        return new ResponseEntity<>(
                new ErrorResponse(errorMessage, status, path),
                new HttpHeaders(),
                httpStatus
        );
    }
}
