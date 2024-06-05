package com.proyectoSpringBoot.services.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoSpringBoot.persistence.entities.Tarea;
import com.proyectoSpringBoot.persistence.entities.enumerados.Estado;
import com.proyectoSpringBoot.persistence.repositories.TareaRepository;

@Service
public class TareaService {
	
	@Autowired
	private TareaRepository tareaRepository;
	
	public List<Tarea> getTareas(){
		return this.tareaRepository.findAll();
	}
	
	public Optional<Tarea> getTarea(int idTarea){
		return this.tareaRepository.findById(idTarea);
	}
	
	public Tarea crearTarea(Tarea tarea) {
		tarea.setEstado(Estado.PENDIENTE);
		tarea.setFechaCreacion(LocalDate.now());
		
		return this.tareaRepository.save(tarea);
	}
	
	public Tarea actualizarTarea(Tarea tarea) {
		return this.tareaRepository.save(tarea);
	}
	
	public boolean borrarTarea(int idTarea) {
		boolean result = false;
		
		if(this.tareaRepository.findById(idTarea).isPresent()) {
			this.tareaRepository.deleteById(idTarea);
			result = true;
		}
		
		return result;
	}
	
	public Optional<List<Tarea>> getTareaPendiente() {
        return this.tareaRepository.findByStatus(Estado.PENDIENTE);
    }

    public Optional<List<Tarea>> getTareaEnProceso() {
        return this.tareaRepository.findByStatus(Estado.EN_PROCESO);
    }

    public Optional<List<Tarea>> getTareaCompletada() {
        return this.tareaRepository.findByStatus(Estado.COMPLETADA);
    }
    
    public List<Tarea> getTareaVencida() {
        return this.tareaRepository.findByFechaVencimientoBefore(LocalDate.now());
    }
    
    public List<Tarea> getTareaNoVencida() {
        return this.tareaRepository.findByFechaVencimientoAfter(LocalDate.now());
    }
    
    public List<Tarea> getTareasEmpiezanPorP(){
    	return this.tareaRepository.findByTituloStartingWith("P");
    }
    
    public List<Tarea> getTareasIdMayorQue4(){
    	return this.tareaRepository.findByIdGreaterThan(4);
    }
    
    public List<Tarea> getTareaByTitulo(String titulo) {
        return this.tareaRepository.findByTituloStartingWith(titulo);
    }

}
