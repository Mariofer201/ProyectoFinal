function abrirDialog() {
    PF('myDialog').show();
}

function guardarAministrador() {
    var nombre = encodeURIComponent(document.getElementById('inputproducto').value);
    var username = encodeURIComponent(document.getElementById('inputusername').value);
    var contraseña = encodeURIComponent(document.getElementById('inputcontraseña').value);
    var comparaciones = encodeURIComponent(document.getElementById('inputcomparaciones').value);
    var venderacciones = encodeURIComponent(document.getElementById('inputvenderacciones').value);

    // Realizar la solicitud para guardar el producto
    var url = "http://localhost:8081/api/administrador";
    var xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.onload = function() {
        if (xhr.status === 201) {
            // Guardado exitoso
            console.log("Producto guardado exitosamente");
            // Realizar las acciones adicionales necesarias, como actualizar la tabla
        } else {
            // Error al guardar el producto
            console.error("Error al guardar el producto:", xhr.responseText);
        }
    };
    xhr.send("nombre=" + nombre + "&username=" + username + "&contraseña=" + contraseña + "&comparaciones=" + comparaciones + "&venderacciones=" + venderacciones);

    alert("Producto guardado exitosamente");
}

var url = "http://localhost:8081/api/administrador";
var xhr = new XMLHttpRequest();
xhr.open('GET', url, true);

xhr.onload = function() {
    if (xhr.status == 200) {
        var productos = JSON.parse(xhr.responseText);
        generarFilasTabla(productos);
    } else {
        console.error("Error al obtener los productos:", xhr.responseText);
    }
};
xhr.send(null);

function generarFilasTabla(productos) {
    var tbody = document.getElementById("tablaProductosBody");
    tbody.innerHTML = "";

    for (var i = 0; i < productos.length; i++) {
        var producto = productos[i];

        var fila = document.createElement("tr");
        var columnaID = document.createElement("td");
        var columnaNombre = document.createElement("td");
        var columnaUsername = document.createElement("td");
        var columnaContraseña = document.createElement("td");
        var columnaComparaciones = document.createElement("td");
        var columnaVenderAcciones = document.createElement("td");
        var columnaFunciones = document.createElement("td");

        columnaID.textContent = producto.ID;
        columnaNombre.textContent = producto.nombre;
        columnaUsername.textContent = producto.username;
        columnaContraseña.textContent = producto.contraseña;
        columnaComparaciones.textContent = producto.comparaciones;
        columnaVenderAcciones.textContent = producto.venderacciones;

        // Crear el botón de Editar
        var botonEditar = document.createElement("button");
        botonEditar.setAttribute("type", "button");
        botonEditar.classList.add("btn", "btn-primary");
        botonEditar.innerHTML = 'Editar <i class="bi bi-check"></i>';

        botonEditar.addEventListener("click", function(event) {
            var productoData = JSON.parse(event.target.getAttribute("data-producto"));
            editarProducto(productoData);
        });

        // Agregar atributo "data-producto" al botón de Editar
        botonEditar.setAttribute("data-producto", JSON.stringify(producto));

        // Crear el botón de Eliminar
        var botonEliminar = document.createElement("button");
        botonEliminar.setAttribute("type", "button");
        botonEliminar.classList.add("btn", "btn-danger");
        botonEliminar.innerHTML = 'Eliminar <i class="bi bi-x"></i>';

        botonEliminar.addEventListener("click", function(event) {
            var productoData = JSON.parse(event.target.getAttribute("data-producto"));
            eliminarProducto(productoData);
        });

        // Agregar atributo "data-producto" al botón de Eliminar
        botonEliminar.setAttribute("data-producto", JSON.stringify(producto));

        columnaFunciones.appendChild(botonEditar);
        columnaFunciones.appendChild(botonEliminar);

        fila.appendChild(columnaID);
        fila.appendChild(columnaNombre);
        fila.appendChild(columnaUsername);
        fila.appendChild(columnaContraseña);
        fila.appendChild(columnaComparaciones);
        fila.appendChild(columnaVenderAcciones);
        fila.appendChild(columnaFunciones);

        tbody.appendChild(fila);
    }
}

function editarProducto(producto) {
    var id = producto.ID;
    var nuevoNombre = encodeURIComponent(prompt("Ingrese el nuevo nombre:", producto.nombre));
    var nuevoUsername = encodeURIComponent(prompt("Ingrese el nuevo username:", producto.username));
    var nuevaContraseña = encodeURIComponent(prompt("Ingrese la nueva contraseña:", producto.contraseña));
    var nuevasComparaciones = encodeURIComponent(prompt("Ingrese las nuevas comparaciones:", producto.comparaciones));
    var nuevasVenderAcciones = encodeURIComponent(prompt("Ingrese las nuevas acciones para vender:", producto.venderacciones));

    // Validar que se haya ingresado un nuevo nombre
    if (nuevoNombre !== null) {
        // Realizar la solicitud para actualizar el producto
        var url = "http://localhost:8081/api/administrador/" + id;
        var xhr = new XMLHttpRequest();
        xhr.open('PUT', url, true);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.onload = function() {
            if (xhr.status === 200) {
                // Actualización exitosa
                console.log("Producto actualizado exitosamente");
                // Realizar las acciones adicionales necesarias, como actualizar la tabla
            } else {
                // Error al actualizar el producto
                console.error("Error al actualizar el producto:", xhr.responseText);
            }
        };
        xhr.send("ID=" + id + "&nombre=" + nuevoNombre + "&username=" + nuevoUsername + "&contraseña=" + nuevaContraseña + "&comparaciones=" + nuevasComparaciones + "&venderacciones=" + nuevasVenderAcciones);
    }
}

function eliminarProducto(producto) {
    var id = producto.ID;
    var confirmacion = confirm("¿Está seguro que desea eliminar el producto?");
    // Validar que se haya ingresado un nuevo nombre
    if (confirmacion) {
        // Realizar la solicitud para eliminar el producto
        var url = "http://localhost:8081/api/administrador/" + id;
        var xhr = new XMLHttpRequest();
        xhr.open('DELETE', url, true);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.onload = function() {
            if (xhr.status === 200) {
                // Eliminación exitosa
                console.log("Producto eliminado exitosamente");
                // Realizar las acciones adicionales necesarias, como actualizar la tabla
            } else {
                // Error al eliminar el producto
                console.error("Error al eliminar el producto:", xhr.responseText);
            }
        };
        xhr.send("ID=" + id);
        alert("Producto eliminado exitosamente");
    }
}
