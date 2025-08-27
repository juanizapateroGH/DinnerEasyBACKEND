package DinnerEasy.demo1.Controller;

import DinnerEasy.demo1.Entity.Reserva;
import DinnerEasy.demo1.Service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reserva")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public List<Reserva> listarReserva(){
        return reservaService.listarReservas();
    }

    @PostMapping
    public ResponseEntity<Reserva>agregarReserva(@RequestBody Reserva reserva){
        Reserva reservaNueva = reservaService.agregarReserva(reserva);
        return new ResponseEntity<>(reservaNueva, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?>modificarReserva(@RequestParam Long id, @RequestBody Reserva reserva){
        try {
            Reserva reservaNueva = reservaService.modidificarReserva(id, reserva);
            return ResponseEntity.ok(reservaNueva);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La reserva con id "+id+" no existe");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String>eliminarReserva(@RequestParam Long id){
        boolean eliminado = reservaService.eliminarReserva(id);
        if (eliminado){
            return new ResponseEntity<>("La reserva con id"+id+" fue eliminado", HttpStatus.OK );

        }else {
            return new ResponseEntity<>("La reserva con id"+id+" no se encontro", HttpStatus.NOT_FOUND);
        }
    }


}

