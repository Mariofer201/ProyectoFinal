package co.edu.unbosque.Proyectos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.Proyectos.model.Empresa;
import co.edu.unbosque.Proyectos.model.Usuario;



@Repository
public interface EmpresaRepository extends CrudRepository<Empresa , Long> {
    Optional<Empresa> findAllById(Integer id);
    Optional<Empresa> findById(Integer id);
    List<Empresa> findAll();
    public Optional<List<Empresa>> findByNombre(String nombre);
    public List<Usuario>findByDescripcion(String descripcion);
    public void deleteById(Integer id); 
    public void deleteByNombre(String nombre);
}