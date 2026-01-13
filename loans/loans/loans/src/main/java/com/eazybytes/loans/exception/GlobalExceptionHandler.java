package com.eazybytes.loans.exception;

import com.eazybytes.loans.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LoanAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleLoanAlreadyExistsException(LoanAlreadyExistsException ex, WebRequest webRequest) {


        ErrorResponseDto errorResponseDto =  ErrorResponseDto.builder()
                .errorTime(LocalDateTime.now())
                .errorMessage(ex.getMessage())
                .errorCode(HttpStatus.BAD_REQUEST)
                .path(webRequest.getDescription(false))
                .build();

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(RespouceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(RespouceNotFoundException ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto =  ErrorResponseDto.builder()
                .errorTime(LocalDateTime.now())
                .errorMessage(ex.getMessage())
                .errorCode(HttpStatus.NOT_FOUND)
                .path(webRequest.getDescription(false))
                .build();

        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
}
