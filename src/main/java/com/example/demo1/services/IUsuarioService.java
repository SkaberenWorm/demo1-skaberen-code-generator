package com.example.demo1.services;

import java.util.List;

import com.example.demo1.entities.Usuario;
import com.example.demo1.utils.ResultadoProc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IUsuarioService {

  /**
   * Retrieves an entity {@link Usuario} by its identifier
   * 
   * @param usuarioId Identifier {@link Usuario}
   * @return {@link Usuario} with the given id
   */
  ResultadoProc<Usuario> findById(long usuarioId);

  /**
   * Returns all instances of the type {@link Usuario}
   * 
   * @return all entities {@link Usuario}
   */
  ResultadoProc<List<Usuario>> findAll();

  /**
   * Returns all active instances of the type {@link Usuario}
   * 
   * @return all active entities {@link Usuario}
   */
  ResultadoProc<List<Usuario>> findAllActive();

  /**
   * Saves a given entity {@link Usuario}
   * 
   * @param usuario {@link Usuario}
   * @return the saved entity
   */
  ResultadoProc<Usuario> save(Usuario usuario);

  /**
   * Updates a given entity {@link Usuario}
   * 
   * @param usuario {@link Usuario}
   * @return the updated entity
   */
  ResultadoProc<Usuario> update(Usuario usuario);

  /**
   * Returns a {@link Page} of the {@link Usuario} type that match the search.
   * 
   * @param pageable {@link PageRequest}
   * @param search   Text to search within the attributes of the {@link Usuario}
   *                 entity
   * @return {@link Page} of the {@link Usuario}
   */
  ResultadoProc<Page<Usuario>> findAllPaginatedBySearch(String search, PageRequest pageable);
}
