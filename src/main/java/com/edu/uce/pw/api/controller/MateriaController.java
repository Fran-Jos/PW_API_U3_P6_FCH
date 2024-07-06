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
import org.springframework.web.bind.annotation.RestController;

import com.edu.uce.pw.api.repository.model.Materia;
import com.edu.uce.pw.api.service.IMateriaService;

@RestController
@RequestMapping(path = "/materias")
public class MateriaController {

    @Autowired
    private IMateriaService materiaService;

    @PostMapping
    public void agregar(@RequestBody Materia materia) {
        this.materiaService.agregar(materia);
    }

    @PutMapping(path = "/{id}")
    public void modificar(@RequestBody Materia materia, @PathVariable Integer id) {
        materia.setId(id);
        this.materiaService.modificar(materia);
    }

    @DeleteMapping(path = "/{id}")
    public void borrar(@PathVariable Integer id) {
        this.materiaService.borrar(id);
    }

    @GetMapping(path = "/{id}")
    public Materia buscar(@PathVariable Integer id) {
        return this.materiaService.buscar(id);
    }

        // URL: http://localhost:8080/API/v1.0/Matricula/materias
    @PatchMapping(path = "/{id}")
    public void actualizarParcial(@RequestBody Materia materia, @PathVariable Integer id) {
        materia.setId(id);
        Materia materiaExistente = this.materiaService.buscar(materia.getId());
        if (materia.getNombre() != null) {
            materiaExistente.setNombre(materia.getNombre());
        }
        if (materia.getPrecio() != null) {
            materiaExistente.setPrecio(materia.getPrecio());
        }
        this.materiaService.modificar(materiaExistente);
    }


}