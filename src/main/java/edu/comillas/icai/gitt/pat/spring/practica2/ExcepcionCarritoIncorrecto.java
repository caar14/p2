package edu.comillas.icai.gitt.pat.spring.practica2;


import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ExcepcionCarritoIncorrecto extends RuntimeException {
    private List<FieldError> errores;
    public ExcepcionCarritoIncorrecto(BindingResult result) {
        this.errores = result.getFieldErrors();
    }
    public List<FieldError> getErrores() {
        return errores;
    }
}
