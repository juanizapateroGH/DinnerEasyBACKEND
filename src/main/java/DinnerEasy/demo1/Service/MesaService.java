package DinnerEasy.demo1.Service;

import DinnerEasy.demo1.Entity.Mesa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MesaService {
    List<Mesa> listarMesas();
    Mesa agregarMesa(Mesa nuevaMesa);
    Mesa modificarMesa(Long id, Mesa mesaModificada);
    boolean eliminarMesa(Long id);
}
