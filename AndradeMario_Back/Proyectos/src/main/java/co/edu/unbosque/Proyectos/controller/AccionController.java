package co.edu.unbosque.Proyectos.controller;



import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.Proyectos.model.Accion;
import co.edu.unbosque.Proyectos.repository.AccionRepository;

import java.util.List;
import java.util.Optional;

	@Transactional
	@Controller
	@CrossOrigin(origins = "*")
	@RequestMapping("/api")
	public class AccionController {
	   
	    @Autowired
		private AccionRepository opres;
	        @PostMapping("/accion")
	        public ResponseEntity<String> agregar(@RequestParam String nombre, @RequestParam double precio, @RequestParam String fecha, @RequestParam String empresa) {
	    		Accion accion = new Accion();
	    		accion.setNombre(nombre);
	    		accion.setPrecio(precio);
	    		accion.setFecha(fecha);
	    		accion.setEmpresa(empresa);
	    		opres.save(accion);
	            return ResponseEntity.status(HttpStatus.CREATED).body("Dato creado con éxito: 201");
	        }

	        @GetMapping("/operacion")
	        public ResponseEntity<List<Accion>> traerTodo() {
	            List<Accion> lista = opres.findAll();
	            if (lista.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	            }
	            return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
	        }

	        @GetMapping("/operacion/{id}")
	        public ResponseEntity<Optional<Accion>> mostrarPorID(@PathVariable Integer id) {
	            Optional<Accion> dato = opres.findById(id);
	            if (dato.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	            }
	            return ResponseEntity.status(HttpStatus.ACCEPTED).body(dato);
	        }

	        @DeleteMapping("/operacion/{id}")
	        public ResponseEntity<String> eliminarPorID(@PathVariable Integer id) {
	            Optional<Accion> dato = opres.findById(id);
	            if (dato.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar el dato");
	            }
	            opres.deleteById(id);
	            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminado exitosamente");
	        }

	        @PutMapping("/operacion/{id}")
	        public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestParam String nombre, @RequestParam double precio, @RequestParam String fecha, @RequestParam String empresa) {
	            Optional<Accion> dato = opres.findById(id);
	            if (dato.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo actualizar el dato");
	            }
	            Accion accion = dato.get();
	            accion.setNombre(nombre);
	            accion.setPrecio(precio);
	            accion.setFecha(fecha);
	            accion.setEmpresa(empresa);
	            opres.save(accion);
	            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Dato actualizado exitosamente");
	        }

	        @DeleteMapping("/operacion/nombre/{nombre}")
	        public ResponseEntity<String> eliminarPorNombre(@PathVariable String nombre) {
	            List<Accion> datos = opres.findByNombre(nombre);
	            if (datos.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Datos no encontrados");
	            }
	            opres.deleteByNombre(nombre);
	            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminados exitosamente");
	        }
	    }
