package com.example.demo1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.utils.ResultadoProc;
import com.example.demo1.utils.SearchPagination;
import com.example.demo1.entities.Usuario;

import com.example.demo1.services.IUsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {

	@Autowired
	IUsuarioService usuarioService;


	@GetMapping("/{id}")
	public ResponseEntity<ResultadoProc<Usuario>> findById(@PathVariable("id") long usuarioId) {
		ResultadoProc<Usuario> salida = usuarioService.findById(usuarioId);
		return new ResponseEntity<ResultadoProc<Usuario>>(salida, HttpStatus.OK);
	}

	@GetMapping("/find-all")
		public ResponseEntity<ResultadoProc<List<Usuario>>> findAll() {
			ResultadoProc<List<Usuario>> salida = usuarioService.findAll();
			return new ResponseEntity<ResultadoProc<List<Usuario>>>(salida, HttpStatus.OK);
		}

	@GetMapping("/find-all-active")
		public ResponseEntity<ResultadoProc<List<Usuario>>> findAllActive() {
			ResultadoProc<List<Usuario>> salida = usuarioService.findAllActive();
			return new ResponseEntity<ResultadoProc<List<Usuario>>>(salida, HttpStatus.OK);
		}

	@PostMapping
	public ResponseEntity<ResultadoProc<Usuario>> save(@RequestBody Usuario usuario) {
		ResultadoProc<Usuario> salida = usuarioService.save(usuario);
		return new ResponseEntity<ResultadoProc<Usuario>>(salida, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<ResultadoProc<Usuario>> update(@RequestBody Usuario usuario) {
		ResultadoProc<Usuario> salida = usuarioService.update(usuario);
		return new ResponseEntity<ResultadoProc<Usuario>>(salida, HttpStatus.OK);
	}

	@PostMapping("/page-all-by-search")
		public ResponseEntity<ResultadoProc<Page<Usuario>>> findAllPaginatedBySearch(
				@RequestBody SearchPagination<String> searchPagination) {
			PageRequest pageable = searchPagination.getPageRequest();
			String search = searchPagination.getSeek();
			ResultadoProc<Page<Usuario>> salida = usuarioService.findAllPaginatedBySearch(search,
				pageable);
			return new ResponseEntity<ResultadoProc<Page<Usuario>>>(salida, HttpStatus.OK);
		}
}