package DinnerEasy.demo1.Service.Impl;

import DinnerEasy.demo1.Entity.Reserva;
import DinnerEasy.demo1.Repository.ReservaRepository;
import DinnerEasy.demo1.Service.ReservaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImp implements ReservaService {
    private final ReservaRepository reservaRepository;

    public ReservaServiceImp(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva agregarReserva(Reserva reservaNueva) {
        reservaRepository.save(reservaNueva);
        return reservaNueva;
    }

    @Override
    public Reserva modidificarReserva(Long id, Reserva reservaModificada) {
        Reserva reservaExistente = reservaRepository.findById(id).orElse(null);
        if (reservaExistente == null){
            throw new RuntimeException("El id "+id+" no existe");
        }else{
            reservaExistente.setEstado(reservaModificada.getEstado());
            reservaExistente.setFecha(reservaModificada.getFecha());
            reservaExistente.setHora(reservaModificada.getHora());
            reservaExistente.setRestaurante(reservaModificada.getRestaurante());
            reservaExistente.setCantPersonas(reservaModificada.getCantPersonas());
            reservaExistente.setUsuario(reservaModificada.getUsuario());

            return reservaRepository.save(reservaExistente);
        }
    }

    @Override
    public boolean eliminarReserva(Long id) {
        boolean eliminado = reservaRepository.existsById(id);
        if (eliminado){
            reservaRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
