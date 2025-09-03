package DinnerEasy.demo1.Service;

import DinnerEasy.demo1.Entity.Reserva;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReservaService {
    List<Reserva> listarReservas();
    Reserva agregarReserva(Reserva reservaNueva);
    Reserva modidificarReserva(Long id, Reserva reservaModificada);
    boolean eliminarReserva(Long id);
}
