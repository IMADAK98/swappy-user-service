//package com.swappy.userservice.Errors;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(InternalAuthenticationServiceException.class)
//    public ResponseEntity<Object> handleAuthenticationException(InternalAuthenticationServiceException ex) {
//        // Customize the response for the authentication exception
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body("Authentication failed: " + ex.getMessage());
//    }
//
//    // Add more exception handlers for other types of exceptions if needed
//}