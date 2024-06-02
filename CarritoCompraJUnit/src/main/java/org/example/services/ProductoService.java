package org.example.services;

import org.example.models.Producto;

import java.util.List;

public interface ProductoService {
    void agregarProducto(Producto producto);
    void eliminarProducto(String productoId);
    Producto obtenerProducto(String productoId);
    List<Producto> obtenerTodosLosProductos();
}
