package DinnerEasy.demo1.Controller;

import DinnerEasy.demo1.Entity.Plato;
import DinnerEasy.demo1.Service.PlatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/plato")
public class PlatoController {
    private final PlatoService platoService;

    public PlatoController(PlatoService platoService) {
        this.platoService = platoService;
    }

    @GetMapping
    public List<Plato>listarPlato(){
        return platoService.listarPlato();
    }

    @PostMapping
    public ResponseEntity<Plato>agregarPlato(@RequestBody Plato plato){
        Plato platoNuevo = platoService.agregarPlato(plato);
        return new ResponseEntity<>(platoNuevo, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?>modificarPlato(@RequestParam Long id, @RequestBody Plato platoExistente){
            try{
                Plato plato = platoService.modificarPlato(id, platoExistente);
                return ResponseEntity.ok(plato);
            }catch (RuntimeException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El plato con id "+id+" no existe");
            }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String>eliminarPlato(@RequestParam Long id){
        boolean eliminado = platoService.eliminarPlato(id);
        if (eliminado){
            return new ResponseEntity<>("El plato con id "+id+" fue eliminado", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("El plato con id "+id+" no fue encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
