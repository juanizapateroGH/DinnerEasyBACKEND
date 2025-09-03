package DinnerEasy.demo1.Service;

import DinnerEasy.demo1.Entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MenuService {
    List<Menu> listarMenu();
    Menu agregarMenu(Menu menuNuevo);
    Menu modificaraMenu(Long id, Menu menuModificado);
    Boolean eliminarMenu(Long id);
}
