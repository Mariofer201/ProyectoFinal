package co.edu.unbosque.Proyectos.controller;


import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.Proyectos.model.Usuario;
import co.edu.unbosque.Proyectos.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Controller
@Transactional
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class  AdministradorController {
	
    @Autowired
    private UsuarioRepository opres;
    @PostMapping("/administrador")
    public ResponseEntity<String> agregar(@RequestParam String nombre, @RequestParam String contraseña, @RequestParam double comprarAcciones, @RequestParam double venderAcciones) {
        Usuario usua = new Usuario();
        usua.setNombre(nombre);
        usua.setContraseña(contraseña);
        usua.setCompraracciones(comprarAcciones);
        usua.setVenderacciones(venderAcciones);
        opres.save(usua);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dato creado con éxito: 201");
    }

    @GetMapping("/operacion")
    public ResponseEntity<List<Usuario>> traerTodo() {
        List<Usuario> lista = opres.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
    }

    @GetMapping("/operacion/{id}")
    public ResponseEntity<Optional<Usuario>> mostrarPorID(@PathVariable Integer id) {
        Optional<Usuario> dato = opres.findById(id);
        if (dato.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dato);
    }

    @DeleteMapping("/operacion/{id}")
    public ResponseEntity<String> eliminarPorID(@PathVariable Integer id) {
        Optional<Usuario> dato = opres.findById(id);
        if (dato.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar el dato");
        }
        opres.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminado exitosamente");
    }

    @PutMapping("/operacion/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestParam String nombre, @RequestParam String contraseña, @RequestParam double comprarAcciones, @RequestParam double venderAcciones) {
        Optional<Usuario> dato = opres.findById(id);
        if (dato.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo actualizar el dato");
        }
        Usuario temp = dato.get();
        temp.setNombre(nombre);
        temp.setContraseña(contraseña);
        temp.setCompraracciones(comprarAcciones);
        temp.setVenderacciones(venderAcciones);
        opres.save(temp);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Dato actualizado exitosamente");
    }

    @DeleteMapping("/operacion/compraracciones/{comprarAcciones}")
    public ResponseEntity<String> eliminarPorComprarAcciones(@PathVariable double comprarAcciones) {
        Optional<List<Usuario>> dato = opres.findBycompraracciones(comprarAcciones);
        List<Usuario> temp = dato.get();
        if (temp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Datos no encontrados");
        }
        opres.deleteBycompraracciones(comprarAcciones);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminado exitosamente");
    }

    @DeleteMapping("/operacion/venderacciones/{venderAcciones}")
    public ResponseEntity<String> eliminarPorVenderAcciones(@PathVariable double venderAcciones) {
        Optional<List<Usuario>> dato = opres.findByvenderacciones(venderAcciones);
        List<Usuario> temp = dato.get();
        if (temp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Datos no encontrados");
        }
        opres.deleteByvenderacciones(venderAcciones);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminado exitosamente");
    }
}	