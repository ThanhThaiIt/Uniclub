package com.Tsoft.UniClub.exception;


import com.Tsoft.UniClub.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralException {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleException(Exception ex) {

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(500);
        baseResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }
}
