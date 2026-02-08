package edu.comillas.icai.gitt.pat.spring.practica2;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Carrito(
        @NotBlank(message = "El id del carrito no puede estar vacío")
        String idCarrito,
        @NotBlank(message = "El id del artículo no puede ser nulo")
        String idArticulo,
        @NotBlank(message = "La descripción no puede estar vacía")
        String descripcion,
        @NotNull(message = "Las unidades no pueden ser nulas")
        Integer unidades,
        @NotNull(message = "El precio no puede ser nulo")
        Float precioFinal){
}
