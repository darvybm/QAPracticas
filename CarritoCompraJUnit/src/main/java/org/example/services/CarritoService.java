package org.example.services;

import org.example.models.ItemCarrito;
import org.example.models.Producto;

import java.util.List;

public interface CarritoService {
    void agregarProducto(Producto producto, int cantidad);
    void eliminarProducto(String productoId);
    void modificarCantidad(String productoId, int cantidad);
    double calcularTotal();
    List<ItemCarrito> obtenerItems();
}
