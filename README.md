# DEMO of Skaberen code generator extension for Visual Studio Code
<https://github.com/SkaberenWorm/Skaberen-code-generator-vscode>

## Controler

```java
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
```

## Service - Interfaz

```java
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

```

## Service - Implementation

```java
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
```
