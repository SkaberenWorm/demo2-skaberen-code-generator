package com.example.demo2.services.impl;

import java.util.List;

import com.example.demo2.entities.Usuario;
import com.example.demo2.exceptions.EntityNotFoundException;
import com.example.demo2.exceptions.ErrorProcessingException;
import com.example.demo2.exceptions.UnsavedEntityException;
import com.example.demo2.repositories.UsuarioRepository;
import com.example.demo2.services.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioService implements IUsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public Usuario findById(final long usuarioId) throws ErrorProcessingException, EntityNotFoundException {
    try {
      // TODO: Add message EntityNotFoundException
      return this.usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFoundException());
    } catch (final EntityNotFoundException e) {
      throw e;
    } catch (final Exception e) {
      log.error("Usuario findById(\"{}\"): {}", usuarioId, e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  @Override
  public List<Usuario> findAll() throws ErrorProcessingException {
    try {
      return this.usuarioRepository.findAll();
    } catch (final Exception e) {
      log.error("Usuario findAll(): {}", e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  @Override
  public List<Usuario> findAllActive() throws ErrorProcessingException {
    try {
      return this.usuarioRepository.findAllActive();
    } catch (final Exception e) {
      log.error("Usuario findAllActive(): {}", e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }

  @Override
  public Usuario save(final Usuario usuario) throws UnsavedEntityException {
    try {
      return this.usuarioRepository.save(usuario);
    } catch (final Exception e) {
      log.error("Usuario save(\"{}\"): {}", usuario.toString(), e.getMessage());
      throw new UnsavedEntityException(e.getMessage());
    }
  }

  @Override
  public Usuario update(final Usuario usuario) throws UnsavedEntityException {
    try {
      return this.usuarioRepository.save(usuario);
    } catch (final Exception e) {
      log.error("Usuario update(\"{}\"): {}", usuario.toString(), e.getMessage());
      throw new UnsavedEntityException(e.getMessage());
    }
  }

  @Override
  public Page<Usuario> findAllPaginatedBySearch(final String search, final PageRequest pageable)
      throws ErrorProcessingException {
    try {
      return this.usuarioRepository.findAllBySearch(search, pageable);
    } catch (final Exception e) {
      log.error("Usuario findAllPaginatedBySearch(\"{}\"): {}", search, e.getMessage());
      throw new ErrorProcessingException(e.getMessage());
    }
  }
}
