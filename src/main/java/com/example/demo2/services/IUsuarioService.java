package com.example.demo2.services;

import java.util.List;

import com.example.demo2.entities.Usuario;
import com.example.demo2.exceptions.EntityNotFoundException;
import com.example.demo2.exceptions.ErrorProcessingException;
import com.example.demo2.exceptions.UnsavedEntityException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IUsuarioService {

  /**
   * Retrieves an entity {@link Usuario} by its identifier
   * 
   * @param usuarioId Identifier {@link Usuario}
   * @return {@link Usuario} with the given id
   * @throws ErrorProcessingException
   * @throws EntityNotFoundException
   */
  Usuario findById(long usuarioId) throws ErrorProcessingException, EntityNotFoundException;

  /**
   * Returns all instances of the type {@link Usuario}
   * 
   * @return all entities {@link Usuario}
   * @throws ErrorProcessingException
   */
  List<Usuario> findAll() throws ErrorProcessingException;

  /**
   * Returns all active instances of the type {@link Usuario}
   * 
   * @return all active entities {@link Usuario}
   * @throws ErrorProcessingException
   */
  List<Usuario> findAllActive() throws ErrorProcessingException;

  /**
   * Saves a given entity {@link Usuario}
   * 
   * @param usuario {@link Usuario}
   * @return the saved entity
   * @throws UnsavedEntityException
   */
  Usuario save(Usuario usuario) throws UnsavedEntityException;

  /**
   * Updates a given entity {@link Usuario}
   * 
   * @param usuario {@link Usuario}
   * @return the updated entity
   * @throws UnsavedEntityException
   */
  Usuario update(Usuario usuario) throws UnsavedEntityException;

  /**
   * Returns a {@link Page} of the {@link Usuario} type that match the search.
   * 
   * @param pageable {@link PageRequest}
   * @param search   Text to search within the attributes of the {@link Usuario}
   *                 entity
   * @return {@link Page} of the {@link Usuario}
   * @throws ErrorProcessingException
   */
  Page<Usuario> findAllPaginatedBySearch(String search, PageRequest pageable) throws ErrorProcessingException;
}
