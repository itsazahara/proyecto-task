package com.proyectoSpringBoot.persistence.crud;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.proyectoSpringBoot.persistence.entities.Tarea;
import com.proyectoSpringBoot.persistence.entities.enumerados.Estado;

public interface TareaCrudRepository extends CrudRepository<Tarea, Integer >{
	
	Optional<List<Tarea>> findByEstado(Estado estado);
	
	List<Tarea> findByFechaVencimientoBefore(LocalDate fechaVencimiento);
	List<Tarea> findByFechaVencimientoAfter(LocalDate fechaNoVencimiento);
	
	List<Tarea> findByTituloStartingWith(String titulo);
	
	List<Tarea> findByIdGreaterThan(int id);
	


}
