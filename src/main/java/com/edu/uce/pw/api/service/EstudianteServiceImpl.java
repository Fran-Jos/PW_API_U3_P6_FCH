package com.edu.uce.pw.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.uce.pw.api.repository.IEstudianteRepository;
import com.edu.uce.pw.api.repository.model.Estudiante;
import com.edu.uce.pw.api.service.to.EstudianteTO;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

	@Autowired
	private IEstudianteRepository estudianteRepository;

	@Override
	public void guardar(Estudiante e) {
		// TODO Auto-generated method stub
		this.estudianteRepository.insertar(e);
	}

	@Override
	public Estudiante buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.estudianteRepository.seleccionar(id);
	}

	@Override
	public void actualizar(Estudiante e) {
		// TODO Auto-generated method stub
		this.estudianteRepository.actualizar(e);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.estudianteRepository.eliminar(id);
	}

	@Override
	public List<Estudiante> buscarPorGenero(String genero) {
		// TODO Auto-generated method stub
		return this.estudianteRepository.buscarPorGenero(genero);
	}

	public EstudianteTO convertirTO(Estudiante e) {
		EstudianteTO eto = new EstudianteTO();
		eto.setId(e.getId());
		eto.setNombre(e.getNombre());
		eto.setApellido(e.getApellido());
		eto.setFecha(e.getFecha());
		eto.setGenero(e.getGenero());
		return eto;
	}

	public Estudiante convertirESTU(EstudianteTO eto) {
		Estudiante e = new Estudiante();
		e.setId(eto.getId());
		e.setNombre(eto.getNombre());
		e.setApellido(eto.getApellido());
		e.setFecha(eto.getFecha());
		e.setGenero(eto.getGenero());
		return e;
	}

	@Override
	public EstudianteTO buscarId(Integer id) {
		// TODO Auto-generated method stub
		Estudiante e = this.estudianteRepository.seleccionar(id);
		return this.convertirTO(e);
	}

	@Override
	public List<EstudianteTO> buscarTodos() {
		List<Estudiante> lista = this.estudianteRepository.buscarTodos();
		List<EstudianteTO> listaTO = new ArrayList<>();

		for (Estudiante estudiante : lista) {
			listaTO.add(this.convertirTO(estudiante));
		}
		return listaTO;
	}

	@Override
	public EstudianteTO buscarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		Estudiante e = this.estudianteRepository.buscarPorCedula(cedula);
		return this.convertirTO(e);
	}
}
