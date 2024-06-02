import org.example.models.Producto;
import org.example.services.ProductoService;
import org.example.servicesImpl.ProductoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ProductoTest {

    private ProductoService productoService;

    @BeforeEach
    public void setUp() {
        productoService = new ProductoServiceImpl();
    }

    @Test
    public void testAgregarYObtenerProducto() {
        Producto producto = new Producto("1", "Producto 1", 10.0);
        productoService.agregarProducto(producto);
        Producto obtenido = productoService.obtenerProducto("1");
        assertEquals(producto, obtenido);
    }

    @Test
    public void testEliminarProducto() {
        Producto producto = new Producto("1", "Producto 1", 10.0);
        productoService.agregarProducto(producto);
        productoService.eliminarProducto("1");
        assertNull(productoService.obtenerProducto("1"));
    }

    @Test
    public void testAgregarProductoDuplicado() {
        Producto producto1 = new Producto("1", "Producto 1", 10.0);
        Producto producto2 = new Producto("1", "Producto 1 Actualizado", 15.0);
        productoService.agregarProducto(producto1);
        productoService.agregarProducto(producto2);
        Producto obtenido = productoService.obtenerProducto("1");
        assertEquals(producto2, obtenido);
    }

    @Test
    public void testObtenerProductoNoExistente() {
        Producto obtenido = productoService.obtenerProducto("999");
        assertNull(obtenido);
    }

    @Test
    public void testEliminarProductoNoExistente() {
        Producto producto = new Producto("1", "Producto 1", 10.0);
        productoService.agregarProducto(producto);
        productoService.eliminarProducto("999");
        Producto obtenido = productoService.obtenerProducto("1");
        assertNotNull(obtenido);
    }

    @Test
    public void testObtenerTodosLosProductos() {
        Producto producto1 = new Producto("1", "Producto 1", 10.0);
        Producto producto2 = new Producto("2", "Producto 2", 20.0);
        productoService.agregarProducto(producto1);
        productoService.agregarProducto(producto2);
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        assertEquals(2, productos.size());
        assertTrue(productos.contains(producto1));
        assertTrue(productos.contains(producto2));
    }

    @Test
    public void testPrecioNegativo() {
        Producto producto1 = new Producto("1", "Producto 1", -130.00);
        assertThrows(IllegalArgumentException.class, () -> productoService.agregarProducto(producto1));
    }
}
