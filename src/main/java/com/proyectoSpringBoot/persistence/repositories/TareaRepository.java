package com.proyectoSpringBoot.persistence.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proyectoSpringBoot.persistence.crud.TareaCrudRepository;
import com.proyectoSpringBoot.persistence.entities.Tarea;
import com.proyectoSpringBoot.persistence.entities.enumerados.Estado;

@Repository
public class TareaRepository {
	
	@Autowired
	private TareaCrudRepository tareaCrudRepository;
	
	public List<Tarea> findAll(){
		return (List<Tarea>) this.tareaCrudRepository.findAll();
	}
	
	public Optional<Tarea> findById(int idTarea){
		return this.tareaCrudRepository.findById(idTarea);
	}
	
	public Tarea save(Tarea tarea) {
		return this.tareaCrudRepository.save(tarea);
	}
	
	public void deleteById(int idTarea) {
		this.tareaCrudRepository.deleteById(idTarea);
	}
	
	public Optional<List<Tarea>> findByStatus(Estado estado) {
        return this.tareaCrudRepository.findByEstado(estado);
    }
	
	public List<Tarea> findByFechaVencimientoBefore(LocalDate fechaVencimiento) {
        return this.tareaCrudRepository.findByFechaVencimientoBefore(fechaVencimiento);
    }
	
	public List<Tarea> findByFechaVencimientoAfter(LocalDate fechaNoVencimiento) {
        return this.tareaCrudRepository.findByFechaVencimientoAfter(fechaNoVencimiento);
    }
	
	public List<Tarea> findByTituloStartingWith(String titulo){
		return this.tareaCrudRepository.findByTituloStartingWith(titulo);
	}
	
	public List<Tarea> findByIdGreaterThan(int id){
		return this.tareaCrudRepository.findByIdGreaterThan(id);
	}

}
