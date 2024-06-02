package org.example.servicesImpl;

import org.example.models.ItemCarrito;
import org.example.services.ItemCarritoService;

import java.util.ArrayList;
import java.util.List;

public class ItemCarritoServiceImpl implements ItemCarritoService {
    private List<ItemCarrito> items = new ArrayList<>();

    @Override
    public void agregarItem(ItemCarrito item) {
        items.add(item);
    }

    @Override
    public void eliminarItem(String productoId) {
        items.removeIf(item -> item.getProducto().getId().equals(productoId));
    }

    @Override
    public void modificarCantidad(String productoId, int cantidad) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getId().equals(productoId)) {
                item.setCantidad(cantidad);
                return;
            }
        }
    }

    @Override
    public List<ItemCarrito> obtenerItems() {
        return items;
    }
}
