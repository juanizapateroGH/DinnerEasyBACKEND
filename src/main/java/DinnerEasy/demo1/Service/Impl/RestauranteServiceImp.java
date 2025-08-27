package DinnerEasy.demo1.Service.Impl;

import DinnerEasy.demo1.Entity.Restaurante;
import DinnerEasy.demo1.Repository.RestauranteRepository;
import DinnerEasy.demo1.Service.RestauranteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteServiceImp implements RestauranteService {
    private final RestauranteRepository restauranteRepository;

    public RestauranteServiceImp(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }


    @Override
    public List<Restaurante> listarRestaurante() {
        return restauranteRepository.findAll();
    }

    @Override
    public Restaurante agregarRestaurante(Restaurante nuevoRestaurante) {
        restauranteRepository.save(nuevoRestaurante);
        return nuevoRestaurante;
    }

    @Override
    public Restaurante modificarRestaurante(Long id, Restaurante restauranteModificado) {
        Restaurante restauranteExistente = restauranteRepository.findById(id).orElse(null);

        if (restauranteExistente == null){
            throw new RuntimeException("El id "+id+" no existe");
        }else{
            restauranteExistente.setDescripcion(restauranteModificado.getDescripcion());
            restauranteExistente.setMesas(restauranteModificado.getMesas());
            restauranteExistente.setMenus(restauranteModificado.getMenus());
            restauranteExistente.setNombre(restauranteModificado.getNombre());
            restauranteExistente.setHorarioApertura(restauranteModificado.getHorarioApertura());
            restauranteExistente.setHorarioCierre(restauranteModificado.getHorarioCierre());
            restauranteExistente.setReservas(restauranteExistente.getReservas());
            restauranteRepository.save(restauranteExistente);
            return restauranteExistente;
        }
    }

    @Override
    public Boolean eliminarRestaurante(Long id) {
        boolean eliminado = restauranteRepository.existsById(id);
        if (eliminado){
            restauranteRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
