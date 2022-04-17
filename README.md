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
```

## Service - Interfaz

```java
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

```

## Service - Implementation

```java
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

```
