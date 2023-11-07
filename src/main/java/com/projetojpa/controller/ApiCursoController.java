package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entity.ApiCurso;
import com.projetojpa.service.ApiCursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "apicurso", description = "API REST  DE GERENCIAMENTO DE DADOS")
@RestController
@RequestMapping("/apicurso")

public class ApiCursoController {
	
	private final ApiCursoService apiCursoService;
	
	@Autowired
	public ApiCursoController(ApiCursoService apiCursoService) {
		this.apiCursoService = apiCursoService;
	}
	
	@GetMapping("/(id)")
	@Operation(summary = "Localiza por ID")
	public ResponseEntity<ApiCurso> bsucarId(@PathVariable Long id){
		ApiCurso ApiCurso = apiCursoService.buscarId(id);
		if(ApiCurso != null) {
			return ResponseEntity.ok(ApiCurso);
		}
		else {
			return ResponseEntity.notFound().build();	
		}
	}
	
	@GetMapping("/")
	@Operation(summary = "Apresenta tudo")
	public ResponseEntity<List<ApiCurso>> buscarTodos(){
		List<ApiCurso> apiCurso = apiCursoService.buscarTodos();
		return ResponseEntity.ok(apiCurso);
	}
	
	@PostMapping("/")
	@Operation(summary = "Inserindo dados")
	public ResponseEntity<ApiCurso> salvar(@RequestBody @Valid ApiCurso apiCurso){
		ApiCurso salvar = apiCursoService.salvar(apiCurso);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
	}
}
