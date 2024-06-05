package com.proyectoSpringBoot.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoSpringBoot.persistence.entities.Tarea;
import com.proyectoSpringBoot.services.dto.TareaService;


@RestController
@RequestMapping("/tareas")
public class TareaController {
	
	@Autowired
	private TareaService tareaService;
	
	@GetMapping
	public ResponseEntity<List<Tarea>> listAll(){
		return ResponseEntity.ok(this.tareaService.getTareas());
	}
	
	@GetMapping("/{idTarea}")
	public ResponseEntity<Tarea> findOne(@PathVariable int idTarea){
		Optional<Tarea> tarea = this.tareaService.getTarea(idTarea);
		
		if(tarea.isPresent()) {
			return ResponseEntity.ok(tarea.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Tarea> create(@RequestBody Tarea tarea){
		return new ResponseEntity<Tarea>(this.tareaService.crearTarea(tarea), HttpStatus.CREATED);
	}
	
	@PutMapping("/{idTarea}")
	public ResponseEntity<Tarea> update(@PathVariable int idTarea , @RequestBody Tarea tarea){
		if(idTarea == tarea.getId()) {
			if(this.tareaService.getTarea(idTarea).isPresent()) {
				return ResponseEntity.ok(this.tareaService.actualizarTarea(tarea));
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{idTarea}")
	public ResponseEntity<Tarea> delete(@PathVariable int idTarea){
		if(this.tareaService.borrarTarea(idTarea)) {
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/pendientes")
    public ResponseEntity<Optional<List<Tarea>>> listPendientes() {
        return ResponseEntity.ok(this.tareaService.getTareaPendiente());
    }

    @GetMapping("/completada")
    public ResponseEntity<Optional<List<Tarea>>> listFinalizados() {
        return ResponseEntity.ok(this.tareaService.getTareaCompletada());
    }

    @GetMapping("/enProceso")
    public ResponseEntity<Optional<List<Tarea>>> listEnProceso() {
        return ResponseEntity.ok(this.tareaService.getTareaEnProceso());
    }
    
    @GetMapping("/vencida")
    public ResponseEntity <List<Tarea>> vencida() {
        return ResponseEntity.ok(this.tareaService.getTareaVencida());
    }
    
    @GetMapping("/noVencida")
    public ResponseEntity <List<Tarea>> noVencida() {
        return ResponseEntity.ok(this.tareaService.getTareaNoVencida());
    }
    
    @GetMapping("/empiezanP")
    public ResponseEntity<List<Tarea>> empiezanP(){
    	return ResponseEntity.ok(this.tareaService.getTareasEmpiezanPorP());
    }
    
    @GetMapping("/mayores4")
    public ResponseEntity<List<Tarea>> mayores4(){
    	return ResponseEntity.ok(this.tareaService.getTareasIdMayorQue4());
    }
    
    @GetMapping("/titulo")
    public ResponseEntity<List<Tarea>> titulo(@RequestParam String titulo){
    	return ResponseEntity.ok(this.tareaService.getTareaByTitulo(titulo));
    }
    
}
