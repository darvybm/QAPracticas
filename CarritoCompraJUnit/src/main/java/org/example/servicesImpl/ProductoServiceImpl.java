package org.example.servicesImpl;

import org.example.models.Producto;
import org.example.services.ProductoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoServiceImpl implements ProductoService {
    private Map<String, Producto> productos = new HashMap<>();

    @Override
    public void agregarProducto(Producto producto) {
        productos.put(producto.getId(), producto);
    }

    @Override
    public void eliminarProducto(String productoId) {
        productos.remove(productoId);
    }

    @Override
    public Producto obtenerProducto(String productoId) {
        return productos.get(productoId);
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {

        return new ArrayList<>(productos.values());
    }
}
