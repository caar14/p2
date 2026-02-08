package edu.comillas.icai.gitt.pat.spring.practica2;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ControladorRest {
    private final Map<String, Carrito> carritos = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/api/carritos")
    @ResponseStatus(HttpStatus.CREATED)
    public Carrito crea(@Valid @RequestBody Carrito carritoNuevo, BindingResult bindingResult) {
        logger.info("Petición para crear carrito {}", carritoNuevo.idCarrito());
        if (bindingResult.hasErrors()){
            throw new ExcepcionCarritoIncorrecto(bindingResult);
        }
        if (carritos.get(carritoNuevo.idCarrito())!=null){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        carritos.put(carritoNuevo.idCarrito(), carritoNuevo);
        return carritoNuevo;
    }

    @GetMapping("/api/carritos/{idCarrito}")
    public Carrito carrito(@PathVariable String idCarrito, Carrito carrito) {
        if (carritos.get(carrito.idCarrito()) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe ese carrito");
        }
        return carritos.get(idCarrito);
    }

    @PutMapping("/api/carritos/{idCarrito}/incremento/{incremento}")
    public Carrito actualizar(@PathVariable String idCarrito,
                                     @PathVariable Integer incremento, Carrito carrito) {
        if (carritos.get(carrito.idCarrito()) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Carrito carritoActual = carritos.get(idCarrito);
        Carrito carritoActualizado =
                new Carrito(idCarrito, carritoActual.idArticulo(), carritoActual.descripcion(), carritoActual.unidades() + incremento, ((carritoActual.precioFinal())/(carritoActual.unidades()))*(carritoActual.unidades()+incremento));
        carritos.put(idCarrito, carritoActualizado);
        return carritoActualizado;
    }

    @DeleteMapping("/api/carritos/borrar/{idCarrito}")
    @PreAuthorize("hasRole('ADMIN')")
    public void borrar(@PathVariable String idCarrito, Carrito carrito) {
        if (carritos.get(carrito.idCarrito()) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        carritos.remove(idCarrito);
    }

    @ExceptionHandler(ExcepcionCarritoIncorrecto.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ModeloCampoIncorrecto> contadorIncorrecto(ExcepcionCarritoIncorrecto ex) {
        return ex.getErrores().stream().map(error -> new ModeloCampoIncorrecto(
                error.getDefaultMessage(), error.getField(), error.getRejectedValue()
        )).toList();
    }
}
