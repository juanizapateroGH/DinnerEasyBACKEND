package DinnerEasy.demo1.Controller;


import DinnerEasy.demo1.Dto.LoginRequest;
import DinnerEasy.demo1.Entity.Usuario;
import DinnerEasy.demo1.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    @PostMapping("/crear")
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

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody LoginRequest loginRequest) {
        // Llama al método del servicio que creamos en el paso anterior
        Optional<Usuario> usuarioValidado = usuarioService.validarUsuario(
                loginRequest.getEmail(),
                loginRequest.getContrasena()
        );

        // Verifica si el Optional contiene un usuario
        if (usuarioValidado.isPresent()) {
            // Si las credenciales son correctas, devuelve el usuario y un estado 200 OK
            // En una app real, aquí se generaría y devolvería un Token JWT.
            return ResponseEntity.ok(usuarioValidado.get());
        } else {
            // Si las credenciales son incorrectas, devuelve un estado 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos");
        }
    }
}
