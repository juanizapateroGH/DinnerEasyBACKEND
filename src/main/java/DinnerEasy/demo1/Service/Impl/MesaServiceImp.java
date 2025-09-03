package DinnerEasy.demo1.Service.Impl;

import DinnerEasy.demo1.Entity.Mesa;
import DinnerEasy.demo1.Repository.MesaRepository;
import DinnerEasy.demo1.Service.MesaService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MesaServiceImp implements MesaService {
    private final MesaRepository mesaRepository;

    public MesaServiceImp(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @Override
    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    @Override
    public Mesa agregarMesa(Mesa nuevaMesa) {
        mesaRepository.save(nuevaMesa);
        return nuevaMesa;
    }

    @Override
    public Mesa modificarMesa(Long id, Mesa mesaModificada) {
        Mesa mesaExistente = mesaRepository.findById(id).orElse(null);
        if (mesaExistente==null){
            throw new RuntimeException("El id "+id+" no existe");
        }else{
            mesaExistente.setCapacidad(mesaModificada.getCapacidad());
            mesaExistente.setNumero(mesaModificada.getNumero());
            mesaExistente.setDisponibilidad(mesaModificada.getDisponibilidad());
            mesaExistente.setRestaurante(mesaModificada.getRestaurante());
            mesaRepository.save(mesaExistente);
            return mesaExistente;
        }
    }

    @Override
    public boolean eliminarMesa(Long id) {
        boolean eliminado = mesaRepository.existsById(id);
        if (eliminado){
            mesaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
