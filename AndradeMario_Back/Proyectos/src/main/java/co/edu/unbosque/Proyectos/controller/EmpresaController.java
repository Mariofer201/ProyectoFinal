package co.edu.unbosque.Proyectos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

import co.edu.unbosque.Proyectos.model.Empresa;
import co.edu.unbosque.Proyectos.repository.EmpresaRepository;
import jakarta.transaction.Transactional;

	@Transactional
	@Controller
	@CrossOrigin(origins = "*")
	@RequestMapping("/api")
	public class EmpresaController {
	    @Autowired
	    private EmpresaRepository opres;

	    @PostMapping("/operacion")
	    public ResponseEntity<String> agregar(@RequestParam String nombre, @RequestParam String descripcion) {
	        Empresa emp = new Empresa();
	        emp.setNombre(nombre);
	        emp.setDescripcion(descripcion);
	        opres.save(emp);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Dato creado con éxito: 201");
	    }

	    @GetMapping("/operacion")
	    public ResponseEntity<List<Empresa>> traerTodo() {
	        List<Empresa> lista = opres.findAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	        }
	        return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
	    }

	    @GetMapping("/operacion/{id}")
	    public ResponseEntity<Optional<Empresa>> mostrarPorID(@PathVariable Integer id) {
	        Optional<Empresa> dato = opres.findById(id);
	        if (dato.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	        }
	        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dato);
	    }

	    @DeleteMapping("/operacion/{id}")
	    public ResponseEntity<String> eliminarPorID(@PathVariable Integer id) {
	        Optional<Empresa> dato = opres.findById(id);
	        if (dato.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar el dato");
	        }
	        opres.deleteById(id);
	        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminado exitosamente");
	    }

	    @PutMapping("/operacion/{id}")
	    public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestParam String nombre, @RequestParam String descripcion) {
	        Optional<Empresa> dato = opres.findById(id);
	        if (dato.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo actualizar el dato");
	        }
	        Empresa emp = dato.get();
	        emp.setNombre(nombre);
	        emp.setDescripcion(descripcion);
	        opres.save(emp);
	        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Dato actualizado exitosamente");
	    }

	    @DeleteMapping("/operacion/nombre/{nombre}")
	    public ResponseEntity<String> eliminarPorNombre(@PathVariable String nombre) {
	        Optional<List<Empresa>> dato = opres.findByNombre(nombre);
	        List<Empresa> temp = dato.get();
	        if (temp.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Datos no encontrados");
	        }
	        opres.deleteByNombre(nombre);
	        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminado exitosamente");
	    }
	}