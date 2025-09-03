package DinnerEasy.demo1.Service;

import DinnerEasy.demo1.Entity.Usuario;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public interface UsuarioService {
    List<Usuario> listarUsuarios();
    Usuario agregarUsuario(Usuario nuevoUsuario);
    Usuario modificarUsuario(Long id, Usuario UsuarioModificado);
    Boolean eliminarUsuario(Long id);
    Optional<Usuario> validarUsuario(String email, String contrasena);
}
