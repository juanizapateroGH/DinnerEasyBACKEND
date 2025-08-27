package DinnerEasy.demo1.Controller;


import DinnerEasy.demo1.Entity.Usuario;
import DinnerEasy.demo1.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    @PostMapping
    public ResponseEntity<Usuario> agregarUsuario(@RequestBody Usuario usuario){
        Usuario usuarioNuevo = usuarioService.agregarUsuario(usuario);
        return new ResponseEntity<>(usuarioNuevo, HttpStatus.CREATED);

    }

    @PutMapping("{id}")
    public ResponseEntity<?>modificarUsuario(@RequestParam Long id, @RequestBody Usuario usuarioExistente){
        try {
            Usuario usuario = usuarioService.modificarUsuario(id, usuarioExistente);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al encontrar el id del usuario");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> elminarUsuario(@RequestParam Long id){
        boolean eliminado = usuarioService.eliminarUsuario(id);
        if (eliminado){
            return new ResponseEntity<>("El usuario con el id "+id+" fue eliminado", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("El usuario con el id "+id+" no existe", HttpStatus.NOT_FOUND);
        }
    }
}
