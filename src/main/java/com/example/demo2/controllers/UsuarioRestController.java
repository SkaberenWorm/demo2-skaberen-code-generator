package com.example.demo2.controllers;

import java.util.List;

import com.example.demo2.entities.Usuario;
import com.example.demo2.exceptions.EntityNotFoundException;
import com.example.demo2.exceptions.ErrorProcessingException;
import com.example.demo2.exceptions.UnsavedEntityException;
import com.example.demo2.services.IUsuarioService;
import com.example.demo2.utils.SearchPagination;

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

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {

	@Autowired
	IUsuarioService usuarioService;

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable("id") long usuarioId)
			throws ErrorProcessingException, EntityNotFoundException {
		Usuario salida = usuarioService.findById(usuarioId);
		return new ResponseEntity<Usuario>(salida, HttpStatus.OK);
	}

	@GetMapping("/find-all")
	public ResponseEntity<List<Usuario>> findAll() throws ErrorProcessingException {
		List<Usuario> salida = usuarioService.findAll();
		return new ResponseEntity<List<Usuario>>(salida, HttpStatus.OK);
	}

	@GetMapping("/find-all-active")
	public ResponseEntity<List<Usuario>> findAllActive() throws ErrorProcessingException {
		List<Usuario> salida = usuarioService.findAllActive();
		return new ResponseEntity<List<Usuario>>(salida, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) throws UnsavedEntityException {
		Usuario salida = usuarioService.save(usuario);
		return new ResponseEntity<Usuario>(salida, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) throws UnsavedEntityException {
		Usuario salida = usuarioService.update(usuario);
		return new ResponseEntity<Usuario>(salida, HttpStatus.OK);
	}

	@PostMapping("/page-all-by-search")
	public ResponseEntity<Page<Usuario>> findAllPaginatedBySearch(
			@RequestBody SearchPagination<String> searchPagination) throws ErrorProcessingException {
		PageRequest pageable = searchPagination.getPageRequest();
		String search = searchPagination.getSeek();
		Page<Usuario> salida = usuarioService.findAllPaginatedBySearch(search,
				pageable);
		return new ResponseEntity<Page<Usuario>>(salida, HttpStatus.OK);
	}
}