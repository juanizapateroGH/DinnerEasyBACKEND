package DinnerEasy.demo1.Service.Impl;

import DinnerEasy.demo1.Entity.Menu;
import DinnerEasy.demo1.Repository.MenuRepository;
import DinnerEasy.demo1.Service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImp implements MenuService {
    private final MenuRepository menuRepository;

    public MenuServiceImp(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> listarMenu() {
        return menuRepository.findAll();
    }

    @Override
    public Menu agregarMenu(Menu menuNuevo) {
        menuRepository.save(menuNuevo);
        return menuNuevo;
    }

    @Override
    public Menu modificaraMenu(Long id, Menu menuModificado) {
        Menu menuExistente = menuRepository.findById(id).orElse(null);
        if (menuExistente == null){
            throw new RuntimeException("El id "+id+" no existe");
        }else{
            menuExistente.setDescripcion(menuModificado.getDescripcion());
            menuExistente.setTipo(menuModificado.getTipo());
            menuExistente.setPlatos(menuModificado.getPlatos());
            menuExistente.setRestaurante(menuModificado.getRestaurante());
            menuRepository.save(menuExistente);
            return  menuExistente;
        }
    }

    @Override
    public Boolean eliminarMenu(Long id) {
        boolean eliminado = menuRepository.existsById(id);
        if (eliminado){
            menuRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
