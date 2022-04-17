package com.example.demo1.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo1.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  

	/**
  * Returns all active instances of the type {@link Usuario} 
  * 
  * @return all active entities {@link Usuario}
  */
  @Query("select p from Usuario p where p.activo = 1")
  List<Usuario> findAllActive();

	/**
  * Returns a {@link Page} of the {@link Usuario} type that match the search.
  * 
  * @param pageable {@link Pageable}
  * @param search   Text to search within the attributes of the {@link Usuario} entity
  * @return {@link Page} of the {@link Usuario}
  */
  // @Query("select p from Usuario p where p._ATRIBUTO_ like %?1%")
  @Query("select p from Usuario p")
  Page<Usuario> findAllBySearch(String search, Pageable pageable);
}
