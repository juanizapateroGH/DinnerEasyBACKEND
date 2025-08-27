package DinnerEasy.demo1.Service;

import DinnerEasy.demo1.Entity.Restaurante;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestauranteService{
    List<Restaurante> listarRestaurante();
    Restaurante agregarRestaurante(Restaurante nuevoRestaurante);
    Restaurante modificarRestaurante(Long id, Restaurante restauranteModificado);
    Boolean eliminarRestaurante(Long id);
}
