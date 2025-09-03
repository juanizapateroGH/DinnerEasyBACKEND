package DinnerEasy.demo1.Service;

import DinnerEasy.demo1.Entity.Plato;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PlatoService {
    List<Plato> listarPlato();
    Plato agregarPlato(Plato platoNuevo);
    Plato modificarPlato(Long id, Plato platoModificado);
    Boolean eliminarPlato(Long id);
}
