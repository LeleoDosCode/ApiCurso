package com.projetojpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.entity.ApiCurso;
import com.projetojpa.repository.ApiCursoRepository;

@Service
public class ApiCursoService {
	private ApiCursoRepository apiCursoRepository;
	
	@Autowired
	public ApiCursoService(ApiCursoRepository apiCursoRepository) {
		this.apiCursoRepository = apiCursoRepository;
	}
	
	public List<ApiCurso> buscarTodos(){
		return apiCursoRepository.findAll();
	}
	
	public ApiCurso buscarId(Long id) {
		Optional<ApiCurso> apiCurso = apiCursoRepository.findById(id);
		return apiCurso.orElse(null);
	}
	
	public ApiCurso salvar(ApiCurso apiCurso) {
		return apiCursoRepository.save(apiCurso);
	}
	
	public ApiCurso alterar(Long id, ApiCurso alterarApiCurso) {
		Optional<ApiCurso> existe = apiCursoRepository.findById(id);
		if (existe.isPresent()) {
			alterarApiCurso.setId(id);
			return apiCursoRepository.save(alterarApiCurso);
		}
		return null;
	}
	
	public boolean apagar(Long id) {
		Optional<ApiCurso> existe = apiCursoRepository.findById(id);
		if(existe.isPresent()) {
			apiCursoRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
