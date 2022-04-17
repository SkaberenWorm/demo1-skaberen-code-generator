package com.example.demo1.services.impl;

import java.util.List;

import com.example.demo1.entities.Usuario;
import com.example.demo1.repositories.UsuarioRepository;
import com.example.demo1.services.IUsuarioService;
import com.example.demo1.utils.ResultadoProc;
import com.example.demo1.utils.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public ResultadoProc<Usuario> findById(final long usuarioId) {
    final ResultadoProc.Builder<Usuario> salida = new ResultadoProc.Builder<Usuario>();
    try {
      final Usuario usuario = this.usuarioRepository.findById(usuarioId).orElse(null);
      if (usuario == null) {
        salida.fallo("Usuario no se encontrado");
      }
      salida.exitoso(usuario);
    } catch (final Exception e) {
      Util.printError("findById(" + usuarioId + ")", e);
      salida.fallo("Se produjo un error inesperado al intentar obtener usuario");
    }
    return salida.build();
  }

  @Override
  public ResultadoProc<List<Usuario>> findAll() {
    final ResultadoProc.Builder<List<Usuario>> salida = new ResultadoProc.Builder<List<Usuario>>();
    try {
      salida.exitoso(this.usuarioRepository.findAll());
    } catch (final Exception e) {
      Util.printError("findAll()", e);
      salida.fallo("Se produjo un error inesperado al intentar listar usuarios");
    }
    return salida.build();
  }

  @Override
  public ResultadoProc<List<Usuario>> findAllActive() {
    final ResultadoProc.Builder<List<Usuario>> salida = new ResultadoProc.Builder<List<Usuario>>();
    try {
      salida.exitoso(this.usuarioRepository.findAllActive());
    } catch (final Exception e) {
      Util.printError("findAllActive()", e);
      salida.fallo("Se produjo un error inesperado al intentar listar usuarios");
    }
    return salida.build();
  }

  @Override
  public ResultadoProc<Usuario> save(final Usuario usuario) {
    final ResultadoProc.Builder<Usuario> salida = new ResultadoProc.Builder<Usuario>();
    try {
      this.usuarioRepository.save(usuario);
      salida.exitoso(usuario, "Usuario  correctamente");
    } catch (final Exception e) {
      Util.printError("save(" + usuario.toString() + ")", e);
      salida.fallo("Se produjo un error inesperado al intentar registrar usuario");
    }
    return salida.build();
  }

  @Override
  public ResultadoProc<Usuario> update(final Usuario usuario) {
    final ResultadoProc.Builder<Usuario> salida = new ResultadoProc.Builder<Usuario>();
    try {
      this.usuarioRepository.save(usuario);
      salida.exitoso(usuario, "Usuario  correctamente");
    } catch (final Exception e) {
      Util.printError("update(" + usuario.toString() + ")", e);
      salida.fallo("Se produjo un error inesperado al intentar actualizar usuario");
    }
    return salida.build();
  }

  @Override
  public ResultadoProc<Page<Usuario>> findAllPaginatedBySearch(final String search, final PageRequest pageable) {
    final ResultadoProc.Builder<Page<Usuario>> salida = new ResultadoProc.Builder<Page<Usuario>>();
    try {
      salida.exitoso(this.usuarioRepository.findAllBySearch(search, pageable));
    } catch (final Exception e) {
      Util.printError("findAllPaginatedBySearch(" + search + ", " + pageable.toString() + ")", e);
      salida.fallo("Se produjo un error inesperado al intentar listar usuarios");
    }
    return salida.build();
  }
}
