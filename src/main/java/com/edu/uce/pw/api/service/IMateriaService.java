package com.edu.uce.pw.api.service;

import java.util.List;

import com.edu.uce.pw.api.repository.model.Materia;

public interface IMateriaService {

	void agregar(Materia materia);

	void modificar(Materia materia);

	Materia buscar(Integer id);

	void borrar(Integer id);
	
	List<Materia> buscarPorCredito(Integer credito);

}
