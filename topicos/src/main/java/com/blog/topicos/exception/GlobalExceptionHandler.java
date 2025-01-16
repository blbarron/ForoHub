package com.blog.topicos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        Map<String, Object> errorDetails = new HashMap<>();
        
        // Extrae dinámicamente datos del contexto de la solicitud o lógica de negocio
        String userId = (String) request.getAttribute("userId", WebRequest.SCOPE_REQUEST);
        String courseName = (String) request.getAttribute("courseName", WebRequest.SCOPE_REQUEST);
        String errorTitle = (String) request.getAttribute("errorTitle", WebRequest.SCOPE_REQUEST);
        
        errorDetails.put("idUsuario", userId != null ? userId : "0");
        errorDetails.put("mensaje", ex.getMessage());
        errorDetails.put("nombreCurso", courseName != null ? courseName : "Desconocido");
        errorDetails.put("titulo", errorTitle != null ? errorTitle : "Error general");
        errorDetails.put("fecha", LocalDateTime.now());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
