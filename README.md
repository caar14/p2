# Práctica 2: Creación API REST

---

## Endpoints

1. POST 

El método POST consiste en añadir un carrito con unos determinados valores a través de la siguiente ruta e introduciendo el siguiente cuerpo. \
Ruta: _http://localhost:8080/api/carritos_ \
Cuerpo: _{
"idCarrito": "C1",
"idArticulo": "A1",
"descripcion": "zanahoria",
"unidades": 3,
"precioFinal":5.5
}_ \
Posibles respuestas:
- 201 Created: Carrito creado correctamente. 
- 400 Bad Request: Datos inválidos.
- 401 Unauthorized: Credenciales incorrectas.
- 409 Conflict : El carrito ya existe.
---
2. GET

El método GET tiene la función de obtener un determinado carrito previamente creado,a partir de su id con la siguiente ruta. \
Ruta: _http://localhost:8080/api/carritos/{id_carrito}_ \
Posibles respuestas:
- 200 OK: El carrito se devuelve.
- 401 Unauthorized: Credenciales incorrectas.
- 404 Not Found: El carrito no existe.
---

3. PUT

El método PUT consiste en actualizar los valores de un determinado carrito, a partir de su id. En el caso de este ejemplo el campo a actualizar será el de unidades del producto, cambiando también el precio final de dicho carrito. \
Ruta: _http://localhost:8080/api/carritos/{id_carrito}/incremento/{incremento}_ \
Posibles respuestas:
- 200 OK: Carrito actualizado.
- 401 Unauthorized: Credenciales incorrectas.
- 404 Not Found: El carrito no existe.
---
4. DELETE

El método DELETE permite borrar un carrito previamente creado a patir de su id. \
Ruta: _http://localhost:8080/api/carritos/borrar/{id_carrito}_ \
Posibles respuestas:
- 200 OK: Carrito eliminado.
- 401 Unauthorized: Credenciales incorrectas para llevar a cabo el borrado.
- 404 Not Found: El carrito no existe.



## Autenticación y autorización

Cabe destacar que se ha añadido seguridad de autenticación básica, según la cual para llevar a cabo cualquiera de los Endpoint es necesario introducir unas credenciales. \
Existen dos tipos de credenciales o usuarios: 
- Usuario básico: Puede ejecutar los endpoint de POST, GET y PUT.
  - Credenciales: 
    - usuario: _usuario_
    - clave: _clave_
- Administrador: Además de poder ejecutar los endpoints anteriores es el único autorizado para eliminar carritos (DELETE)
  - Credenciales:
      - usuario: _admin_
      - clave: _clave_