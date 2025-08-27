package DinnerEasy.demo1.Service.Impl;

import DinnerEasy.demo1.Entity.Usuario;
import DinnerEasy.demo1.Repository.UsuarioRepository;
import DinnerEasy.demo1.Service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario agregarUsuario(Usuario nuevoUsuario) {
        usuarioRepository.save(nuevoUsuario);
        return nuevoUsuario;
    }

    @Override
    public Usuario modificarUsuario(Long id, Usuario usuarioModificado) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente==null){
            throw new RuntimeException("El id "+id+" no existe");
        }else{
            usuarioExistente.setTipo(usuarioModificado.getTipo());
            usuarioExistente.setContrasena(usuarioModificado.getContrasena());
            usuarioExistente.setNombre(usuarioModificado.getNombre());
            usuarioExistente.setEmail(usuarioModificado.getEmail());
            usuarioExistente.setReservas(usuarioModificado.getReservas());
            return usuarioRepository.save(usuarioExistente);
        }
    }

    @Override
    public Boolean eliminarUsuario(Long id) {
        boolean eliminado =usuarioRepository.existsById(id);
        if (eliminado){
            usuarioRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
