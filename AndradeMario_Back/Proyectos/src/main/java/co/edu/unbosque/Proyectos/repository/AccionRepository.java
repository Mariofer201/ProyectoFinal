package co.edu.unbosque.Proyectos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.Proyectos.model.Accion;

public interface AccionRepository extends CrudRepository<Accion, Long> {
	public Optional<Accion> findById(Integer id);
	public List<Accion>findAll();
	public List<Accion>findByNombre(String nombre);
	public List<Accion>findByPrecio(String precio);
	public void deleteById(Integer id); 
	public void deleteByNombre(String nombre); 
}
