package com.edu.uce.pw.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.edu.uce.pw.api.service.IEstudianteService;

@RestController
@RequestMapping(path = "/estudiantes")
public class EstudianteController {

	@Autowired
	private IEstudianteService estudianteService;

	// POST
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/guardar
	//nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes
	@PostMapping
	public void guardar(@RequestBody Estudiante e) {
		this.estudianteService.guardar(e);
	}

	// PUT
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizar
	//nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	@PutMapping(path = "/{id}")
	public void actualizar(@RequestBody Estudiante e, @RequestParam Integer id) {
		e.setId(id);
		this.estudianteService.actualizar(e);
	}

	// PATCH
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizarParcial
	//nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	@PatchMapping(path = "/{id}")
	public void actualizarParcial(@RequestBody Estudiante e , @RequestParam Integer id) {
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
	}

	// DELETE
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar
	//nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	@DeleteMapping(path = "/{id}")
	public void borrar(@PathVariable Integer id) {
		this.estudianteService.borrar(id);
	}

	// GET
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar
	//nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	@GetMapping(path = "/{id}")
	public Estudiante buscar(@PathVariable Integer id) {
		return this.estudianteService.buscar(id);
	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarPorGenero?genero=F&edad=20
	
	@GetMapping(path = "/{}")
	public List<Estudiante> buscarPorGenero(@RequestParam String genero, @RequestParam Integer edad) {
		System.out.println("edad: " + edad);
		List<Estudiante> lista = this.estudianteService.buscarPorGenero(genero);
		return lista;
	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarMixto/1?prueba=holaMundo
	@GetMapping(path = "/buscarMixto/{id}")
	public Estudiante buscarMixto(@PathVariable Integer id, @RequestParam String prueba) {
		System.out.println("dato" + id);
		System.out.println("dato" + prueba);
		return this.estudianteService.buscar(id);
	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarTodos
	@GetMapping(path = "/buscarTodos")
	public List<Estudiante> buscarTodos() {
		return this.estudianteService.buscarTodos();
	}
}
