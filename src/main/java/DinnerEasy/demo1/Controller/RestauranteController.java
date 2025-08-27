package DinnerEasy.demo1.Controller;

import DinnerEasy.demo1.Entity.Restaurante;
import DinnerEasy.demo1.Service.RestauranteService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/restaurante")
public class RestauranteController {
    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public List<Restaurante>listarRestaurante(){
        return restauranteService.listarRestaurante();

    }

    @PostMapping
    public ResponseEntity<Restaurante>agregarRestaunrate(@RequestBody Restaurante restaurante){
        Restaurante restauranteNuevo = restauranteService.agregarRestaurante(restaurante);
        return new ResponseEntity<>(restauranteNuevo, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?>modificarRestaurante(@RequestParam Long id, @RequestBody Restaurante restauranteModificado){
        try{
            Restaurante restauranteNuevo= restauranteService.modificarRestaurante(id,restauranteModificado);
            return ResponseEntity.ok(restauranteNuevo);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El restaurante con id"+id+" no fue encontrado");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String>eliminarRestaurante(@RequestParam Long id){
        boolean eliminado = restauranteService.eliminarRestaurante(id);
        if (eliminado){
            return new ResponseEntity<>("El restaurante con id"+id+" fue eliminado", HttpStatus.OK);
        }else {
            return  new ResponseEntity<>("El restaurante con id"+id+" no fue encontrado", HttpStatus.NOT_FOUND);
        }
    }

}
