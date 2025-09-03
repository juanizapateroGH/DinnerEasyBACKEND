package DinnerEasy.demo1.Service.Impl;

import DinnerEasy.demo1.Entity.Plato;
import DinnerEasy.demo1.Repository.PlatoRepository;
import DinnerEasy.demo1.Service.PlatoService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlatoServiceImp implements PlatoService {
    private final PlatoRepository platoRepository;

    public PlatoServiceImp(PlatoRepository platoRepository) {
        this.platoRepository = platoRepository;
    }

    @Override
    public List<Plato> listarPlato() {
        return platoRepository.findAll();
    }

    @Override
    public Plato agregarPlato(Plato platoNuevo) {
        platoRepository.save(platoNuevo);
        return platoNuevo;
    }

    @Override
    public Plato modificarPlato(Long id, Plato platoModificado) {
        Plato platoExistente = platoRepository.findById(id).orElse(null);
        if (platoExistente == null){
            throw new RuntimeException("El id "+id+" no existe");
        }else{
            platoExistente.setDescripcion(platoModificado.getDescripcion());
            platoExistente.setMenu(platoModificado.getMenu());
            platoExistente.setNombre(platoModificado.getNombre());
            platoExistente.setPrecio(platoModificado.getPrecio());
            platoRepository.save(platoExistente);
            return platoExistente;
        }
    }

    @Override
    public Boolean eliminarPlato(Long id) {
        boolean eliminado = platoRepository.existsById(id);
        if (eliminado){
            platoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
