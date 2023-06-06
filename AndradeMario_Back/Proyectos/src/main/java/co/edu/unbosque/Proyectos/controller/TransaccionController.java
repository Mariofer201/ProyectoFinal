package co.edu.unbosque.Proyectos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.unbosque.Proyectos.model.Accion;
import co.edu.unbosque.Proyectos.model.Transaccion;
import co.edu.unbosque.Proyectos.model.Usuario;
import co.edu.unbosque.Proyectos.repository.AccionRepository;
import co.edu.unbosque.Proyectos.repository.TransaccionRepository;
import co.edu.unbosque.Proyectos.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.transaction.InvalidTransactionException;

public class TransaccionController {
	@Autowired
    private TransaccionRepository daotransaccion;

    @Autowired
    private UsuarioRepository daousuario;

    @Autowired
    private AccionRepository daoaccion;

    @PostMapping
    public Transaccion realizarTransaccion(@RequestBody Transaccion transaccion) throws NotFoundException, InvalidTransactionException {
        Usuario usuario = daousuario.findById(transaccion.getUsuario().getId()).orElseThrow(NotFoundException::new);
        Accion accion = daoaccion.findById(transaccion.getAccion().getId()).orElseThrow(NotFoundException::new);

        if (usuario.getAcciones() >= transaccion.getCantidad()) {
            int accionesDisponibles = usuario.getAcciones() - transaccion.getCantidad();
            usuario.setAcciones(accionesDisponibles);
            daousuario.save(usuario);
            return daotransaccion.save(transaccion);
        } else {
            throw new InvalidTransactionException("El usuario no tiene suficientes acciones disponibles.");
        }
    }
}
