package DinnerEasy.demo1.Controller;

import DinnerEasy.demo1.Entity.Mesa;
import DinnerEasy.demo1.Service.MesaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mesa")
public class MesaController {
    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping
    public List<Mesa> listarMesa(){
        return mesaService.listarMesas();
    }

    @PostMapping
    public ResponseEntity<Mesa> agregarMesa(@RequestBody Mesa mesa){
        Mesa mesaNueva = mesaService.agregarMesa(mesa);
        return new ResponseEntity<>(mesaNueva, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?>modificarMesa(@RequestParam Long id, @RequestBody Mesa mesaExistente){
        try{
            Mesa mesa = mesaService.modificarMesa(id, mesaExistente);
            return ResponseEntity.ok(mesa);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El menu con id"+id+" no existe");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String>eliminarMesa(@RequestParam Long id){
        boolean eliminado = mesaService.eliminarMesa(id);
        if (eliminado){
            return new ResponseEntity<>("El id "+id+" fue eliminado", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("El id "+id+" no fue encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
