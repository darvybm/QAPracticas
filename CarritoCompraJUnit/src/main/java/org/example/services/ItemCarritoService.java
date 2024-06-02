package org.example.services;

import org.example.models.ItemCarrito;
import org.example.models.Producto;

import java.util.List;

public interface ItemCarritoService {
    void agregarItem(ItemCarrito item);
    void eliminarItem(String productoId);
    void modificarCantidad(String productoId, int cantidad);
    List<ItemCarrito> obtenerItems();
}
