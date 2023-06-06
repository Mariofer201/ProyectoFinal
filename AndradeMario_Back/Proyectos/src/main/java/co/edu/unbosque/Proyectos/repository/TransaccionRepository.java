package co.edu.unbosque.Proyectos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.Proyectos.model.Transaccion;

public interface TransaccionRepository extends CrudRepository<Transaccion, Long> {
	public Optional<Transaccion> findById(Integer id);
	public List<Transaccion>findAll();
	public List<Transaccion> findByAccion (String accion);
	public List<Transaccion> findByCantidad(Integer cantidad);

}
