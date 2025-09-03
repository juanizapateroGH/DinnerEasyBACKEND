package DinnerEasy.demo1.Controller;

import DinnerEasy.demo1.Entity.Menu;
import DinnerEasy.demo1.Service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }


    @GetMapping
    public List<Menu>listarMenu(){
        return menuService.listarMenu();
    }

    @PostMapping
    public ResponseEntity<Menu> agreagarMenu(@RequestBody Menu menu){
        Menu menuNuevo = menuService.agregarMenu(menu);
        return new ResponseEntity<>(menuNuevo, HttpStatus.CREATED);
    }


    @PutMapping("{id}")
    public ResponseEntity<?>modificarMenu(@PathVariable Long id, @RequestBody Menu menuExistente){
        try{
            Menu menu = menuService.modificaraMenu(id, menuExistente);
            return ResponseEntity.ok(menu);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El menu con id "+id+" no existe");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String>eliminarMenu(@RequestParam Long id){
        boolean eliminado = menuService.eliminarMenu(id);
        if (eliminado){
            return new ResponseEntity<>( "El capital con id "+id+"fue elimando",HttpStatus.OK);
        }else{
            return new ResponseEntity<>( "El capital con id "+id+" no fue encontrado ",HttpStatus.NOT_FOUND);
        }
    }
}
