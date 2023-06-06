package co.edu.unbosque.Proyectos.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.Proyectos.model.Usuario;



public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	public Optional<Usuario> findById(Integer id);
public List<Usuario>findAll();
public List<Usuario>findByNombre(String nombre);
public Optional<List<Usuario>> findBycompraracciones(double compraracciones);
public Optional<List<Usuario>> findByvenderacciones(double venderacciones);
public void deleteById(Integer id); 
public void deleteByNombre(String nombre); 
public void deleteBycompraracciones(double compraracciones); 
public void deleteByvenderacciones(double venderacciones);
} 	 	
