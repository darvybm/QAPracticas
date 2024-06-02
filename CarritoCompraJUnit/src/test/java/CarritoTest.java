import org.example.models.Producto;
import org.example.services.CarritoService;
import org.example.servicesImpl.CarritoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarritoTest {

    private CarritoService carritoService;
    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    public void setUp() {
        carritoService = new CarritoServiceImpl();
        producto1 = new Producto("1", "Producto 1", 10.0);
        producto2 = new Producto("2", "Producto 2", 20.0);
    }

    @Test
    public void testAgregarYCalcularTotal() {
        carritoService.agregarProducto(producto1, 2);
        carritoService.agregarProducto(producto2, 1);
        assertEquals(40.0, carritoService.calcularTotal());
    }

    @Test
    public void testEliminarProducto() {
        carritoService.agregarProducto(producto1, 2);
        carritoService.agregarProducto(producto2, 1);
        carritoService.eliminarProducto("1");
        assertEquals(20.0, carritoService.calcularTotal());
    }

    @Test
    public void testModificarCantidad() {
        carritoService.agregarProducto(producto1, 2);
        carritoService.modificarCantidad("1", 5);
        assertEquals(50.0, carritoService.calcularTotal());
    }

    @Test
    public void testObtenerItems() {
        carritoService.agregarProducto(producto1, 2);
        assertEquals(1, carritoService.obtenerItems().size());
    }

    @Test
    public void testAgregarProductoConCantidadCeroONegativa() {
        assertThrows(IllegalArgumentException.class, () -> carritoService.agregarProducto(producto1, 0));
        assertThrows(IllegalArgumentException.class, () -> carritoService.agregarProducto(producto1, -1));
        assertEquals(0.0, carritoService.calcularTotal());
    }

    @Test
    public void testEliminarProductoNoExistente() {
        carritoService.agregarProducto(producto1, 2);
        carritoService.eliminarProducto("2");
        assertEquals(20.0, carritoService.calcularTotal());
    }

    @Test
    public void testModificarCantidadACeroONegativa() {
        carritoService.agregarProducto(producto1, 2);
        assertThrows(IllegalArgumentException.class, () -> carritoService.modificarCantidad("1", 0));
        assertThrows(IllegalArgumentException.class, () -> carritoService.modificarCantidad("1", -1));
        assertEquals(20.0, carritoService.calcularTotal());
    }

    @Test
    public void testCalcularTotalConCarritoVacio() {
        assertEquals(0.0, carritoService.calcularTotal());
    }

    @Test
    public void testAgregarProductosDuplicados() {
        carritoService.agregarProducto(producto1, 2);
        carritoService.agregarProducto(producto1, 3);
        assertEquals(50.0, carritoService.calcularTotal());
    }

    @Test
    public void testObtenerItemsConCarritoVacio() {
        assertTrue(carritoService.obtenerItems().isEmpty());
    }

}
