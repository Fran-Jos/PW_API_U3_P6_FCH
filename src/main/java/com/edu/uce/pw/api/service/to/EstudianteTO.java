package com.edu.uce.pw.api.service.to;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.edu.uce.pw.api.repository.model.Materia;



public class EstudianteTO extends RepresentationModel<EstudianteTO>  implements Serializable {
 
    private static final long serialVersionUID = 1L;

	private Integer id;

	private String nombre;

	private String apellido;

	private LocalDateTime fecha;

	private String genero;

    private List<MateriaTO> materia;
	
	// SETTER Y GETTERS
 
    public List<MateriaTO> getMateria() {
        return materia;
    }
    public void setMateria(List<MateriaTO> materia) {
        this.materia = materia;
    }
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
}
