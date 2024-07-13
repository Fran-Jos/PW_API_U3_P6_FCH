package com.edu.uce.pw.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.uce.pw.api.repository.model.Estudiante;
import com.edu.uce.pw.api.repository.model.Materia;
import com.edu.uce.pw.api.service.IEstudianteService;
import com.edu.uce.pw.api.service.IMateriaService;
import com.edu.uce.pw.api.service.to.EstudianteTO;
import com.edu.uce.pw.api.service.to.MateriaTO;

@RestController
@RequestMapping(path = "/estudiantes")
public class EstudianteController {

	@Autowired
	private IEstudianteService estudianteService;

	@Autowired
	private IMateriaService materiaService;

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/guardar
	// Nivel 1: http://localhost:8080/API/v1.0/Matricula/estudiantes
	@PostMapping(consumes = "application/xml", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Estudiante> guardar(@RequestBody Estudiante e) {
		this.estudianteService.guardar(e);
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("MENSAJE201", "GUARDADO CORRECTO");
		return ResponseEntity.status(201).headers(cabeceras).body(e);

	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizar
	// Nivel 1:http://localhost:8080/API/v1.0/Matricula/estudiantes/{id}
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<Estudiante> actualizar(@RequestBody Estudiante e, @PathVariable Integer id) {
		e.setId(id);
		this.estudianteService.actualizar(e);
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("MENSAJE238", "ACTUALIZACION CORRECTA");
		return ResponseEntity.status(238).headers(cabeceras).body(e);
	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizarParcial
	// Nivel 1: http://localhost:8080/API/v1.0/Matricula/estudiantes/{id}
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<Estudiante> actualizarParcial(@RequestBody Estudiante e, @PathVariable Integer id) {
		e.setId(id);
		Estudiante e2 = this.estudianteService.buscar(e.getId());
		if (e.getNombre() != null) {
			e2.setNombre(e.getNombre());
		}
		if (e.getApellido() != null) {
			e2.setApellido(e.getApellido());
		}
		if (e.getFecha() != null) {
			e2.setFecha(e.getFecha());
		}
		this.estudianteService.actualizar(e2);
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("MENSAJE239", "ACTUALIZACION PARCIAL CORRECTA");
		return ResponseEntity.status(239).headers(cabeceras).body(e2);
	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar
	// Nivel 1: http://localhost:8080/API/v1.0/Matricula/estudiantes/{1}
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> borrar(@PathVariable Integer id) {
		this.estudianteService.borrar(id);
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("MENSAJE240", "BORRADO CORRECTO");
		return ResponseEntity.status(240).headers(cabeceras).body("Borrada exitosamente");
	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar/{id}
	// Nivel 1: http://localhost:8080/API/v1.0/Matricula/estudiantes/{id}
	@GetMapping(path = "/{id}", produces = "application/xml")
	public ResponseEntity<Estudiante> buscarPorId(@PathVariable Integer id) {

		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_236", "Corresponde a la consulta de un recurso");
		cabeceras.add("valor", "El que busca encuentra");
		return new ResponseEntity<>(this.estudianteService.buscar(id), cabeceras, 236);
	}

	// http://localhost:8082/API/v1.0/Matricula/estudiantes/genero?genero=F&edad=35
	// Nivel 1: http://localhost:8082/API/v1.0/Matricula/estudiantes/genero?genero=F
	@GetMapping(path = "/genero", produces = "application/json")
	public ResponseEntity<List<Estudiante>> buscarPorGenero(@RequestParam String genero) {
		List<Estudiante> lista = this.estudianteService.buscarPorGenero(genero);
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_236", "Corresponde a la consulta de un recurso");

		return ResponseEntity.status(236).headers(cabeceras).body(lista);
	}

	// http://localhost:8082/API/v1.0/Matricula/estudiantes/buscarMixto/1?edad=15
	// Nivel 1: http://localhost:8082/API/v1.0/Matricula/estudiantes/mixto/1
	@GetMapping(path = "/mixto/{id}", produces = "application/json")
	public ResponseEntity<Estudiante> buscarMixto(@PathVariable Integer id) {
		//return this.estudianteService.buscar(id);
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_236", "BUSQUEDA MIXTA CORRECTA");
		return ResponseEntity.status(236).headers(cabeceras).body(this.estudianteService.buscar(id));
	}

	// Nivel 1: http://localhost:8082/API/v1.0/Matricula/estudiantes//texto/plano
	@GetMapping(path = "/texto/plano")
	public String prueba() {
		String prueba = "Texto de prueba";
		return prueba;
	}

	@GetMapping(path = "/hateoas/{id}", produces = "application/json")
	public EstudianteTO buscarHateoas(@PathVariable Integer id) {
		
		EstudianteTO estudiante = this.estudianteService.buscarId(id);
		List<MateriaTO> lista = this.materiaService.buscarPorIdEstudiante(id);
		estudiante.setMateria(lista);
		return estudiante;
	}

}
