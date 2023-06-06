function abrirDialog() {
    PF('myDialog').show();
}

function guardarAccion() {
    var nombre = encodeURIComponent(document.getElementById('inputproducto').value);
    var precio = encodeURIComponent(document.getElementById('inputprecio').value);
    var fecha = encodeURIComponent(document.getElementById('inputfecha').value);
    var empresa = encodeURIComponent(document.getElementById('inputempresa').value);

    // Realizar la solicitud para guardar la acción
    var url = "http://localhost:8081/api/accion";
    var xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.onload = function() {
        if (xhr.status === 201) {
            // Guardado exitoso
            console.log("Acción guardada exitosamente");
            // Realizar las acciones adicionales necesarias, como actualizar la tabla
        } else {
            // Error al guardar la acción
            console.error("Error al guardar la acción:", xhr.responseText);
        }
    };
    xhr.send("nombre=" + nombre + "&precio=" + precio + "&fecha=" + fecha + "&empresa=" + empresa);

    alert("Acción guardada exitosamente");
}

var url = "http://localhost:8081/api/accion";
var xhr = new XMLHttpRequest();
xhr.open('GET', url, true);

xhr.onload = function() {
    if (xhr.status == 200) {
        var acciones = JSON.parse(xhr.responseText);
        generarFilasTabla(acciones);
    } else {
        console.error("Error al obtener las acciones:", xhr.responseText);
    }
};
xhr.send(null);

function generarFilasTabla(acciones) {
    var tbody = document.getElementById("tablaAccionesBody");
    tbody.innerHTML = "";

    for (var i = 0; i < acciones.length; i++) {
        var accion = acciones[i];

        var fila = document.createElement("tr");
        var columnaID = document.createElement("td");
        var columnaNombre = document.createElement("td");
        var columnaPrecio = document.createElement("td");
        var columnaFecha = document.createElement("td");
        var columnaEmpresa = document.createElement("td");
        var columnaFunciones = document.createElement("td");

        columnaID.textContent = accion.ID;
        columnaNombre.textContent = accion.nombre;
        columnaPrecio.textContent = accion.precio;
        columnaFecha.textContent = accion.fecha;
        columnaEmpresa.textContent = accion.empresa;

        // Crear el botón de Editar
        var botonEditar = document.createElement("button");
        botonEditar.setAttribute("type", "button");
        botonEditar.classList.add("btn", "btn-primary");
        botonEditar.innerHTML = 'Editar <i class="bi bi-check"></i>';

        botonEditar.addEventListener("click", function(event) {
            var accionData = JSON.parse(event.target.getAttribute("data-accion"));
            editarAccion(accionData);
        });

        // Agregar atributo "data-accion" al botón de Editar
        botonEditar.setAttribute("data-accion", JSON.stringify(accion));

        // Crear el botón de Eliminar
        var botonEliminar = document.createElement("button");
        botonEliminar.setAttribute("type", "button");
        botonEliminar.classList.add("btn", "btn-danger");
        botonEliminar.innerHTML = 'Eliminar <i class="bi bi-x"></i>';

        botonEliminar.addEventListener("click", function(event) {
            var accionData = JSON.parse(event.target.getAttribute("data-accion"));
            eliminarAccion(accionData);
        });

        // Agregar atributo "data-accion" al botón de Eliminar
        botonEliminar.setAttribute("data-accion", JSON.stringify(accion));

        columnaFunciones.appendChild(botonEditar);
        columnaFunciones.appendChild(botonEliminar);

        fila.appendChild(columnaID);
        fila.appendChild(columnaNombre);
        fila.appendChild(columnaPrecio);
        fila.appendChild(columnaFecha);
        fila.appendChild(columnaEmpresa);
        fila.appendChild(columnaFunciones);

        tbody.appendChild(fila);
    }
}

function editarAccion(accion) {
    var id = accion.ID;
    var nuevoNombre = encodeURIComponent(prompt("Ingrese el nuevo nombre:", accion.nombre));
    var nuevoPrecio = encodeURIComponent(prompt("Ingrese el nuevo precio:", accion.precio));
    var nuevaFecha = encodeURIComponent(prompt("Ingrese la nueva fecha:", accion.fecha));
    var nuevaEmpresa = encodeURIComponent(prompt("Ingrese la nueva empresa:", accion.empresa));

    // Validar que se haya ingresado un nuevo nombre
    if (nuevoNombre !== null) {
        // Realizar la solicitud para actualizar la acción
        var url = "http://localhost:8081/api/accion/" + id;
        var xhr = new XMLHttpRequest();
        xhr.open('PUT', url, true);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.onload = function() {
            if (xhr.status === 200) {
                // Actualización exitosa
                console.log("Acción actualizada exitosamente");
                // Realizar las acciones adicionales necesarias, como actualizar la tabla
            } else {
                // Error al actualizar la acción
                console.error("Error al actualizar la acción:", xhr.responseText);
            }
        };
        xhr.send("ID=" + id + "&nombre=" + nuevoNombre + "&precio=" + nuevoPrecio + "&fecha=" + nuevaFecha + "&empresa=" + nuevaEmpresa);
    }
}

function eliminarAccion(accion) {
    var id = accion.ID;
    var confirmacion = confirm("¿Está seguro que desea eliminar la acción?");
    // Validar que se haya ingresado un nuevo nombre
    if (confirmacion) {
        // Realizar la solicitud para eliminar la acción
        var url = "http://localhost:8081/api/accion/" + id;
        var xhr = new XMLHttpRequest();
        xhr.open('DELETE', url, true);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.onload = function() {
            if (xhr.status === 200) {
                // Eliminación exitosa
                console.log("Acción eliminada exitosamente");
                // Realizar las acciones adicionales necesarias, como actualizar la tabla
            } else {
                // Error al eliminar la acción
                console.error("Error al eliminar la acción:", xhr.responseText);
            }
        };
        xhr.send("ID=" + id);
        alert("Acción eliminada exitosamente");
    }
}
