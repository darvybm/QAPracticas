import org.example.models.ItemCarrito;
import org.example.models.Producto;
import org.example.services.ItemCarritoService;
import org.example.servicesImpl.ItemCarritoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemCarritoTest {

    private ItemCarritoService itemCarritoService;
    private Producto producto;

    @BeforeEach
    public void setUp() {
        itemCarritoService = new ItemCarritoServiceImpl();
        producto = new Producto("1", "Producto 1", 10.0);
    }

    @Test
    public void testAgregarYObtenerItems() {
        ItemCarrito item = new ItemCarrito(producto, 2);
        itemCarritoService.agregarItem(item);
        assertEquals(1, itemCarritoService.obtenerItems().size());
        assertEquals(item, itemCarritoService.obtenerItems().get(0));
    }

    @Test
    public void testEliminarItem() {
        ItemCarrito item = new ItemCarrito(producto, 2);
        itemCarritoService.agregarItem(item);
        itemCarritoService.eliminarItem("1");
        assertTrue(itemCarritoService.obtenerItems().isEmpty());
    }

    @Test
    public void testModificarCantidad() {
        ItemCarrito item = new ItemCarrito(producto, 2);
        itemCarritoService.agregarItem(item);
        itemCarritoService.modificarCantidad("1", 5);
        assertEquals(5, itemCarritoService.obtenerItems().get(0).getCantidad());
    }
}
