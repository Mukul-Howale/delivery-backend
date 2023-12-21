package com.delivery.Delivery_app.exception;

import com.delivery.Delivery_app.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityException extends ResponseEntityExceptionHandler {

    // Handles all exception
    @ExceptionHandler(Exception.class)
    public final ResponseEntity handleAllException(Exception e, WebRequest webRequest){

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                e.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handles empty values exception
    @ExceptionHandler(WrongIDException.class)
    public final ResponseEntity handleEmptyValueException(Exception e, WebRequest webRequest){

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                e.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
