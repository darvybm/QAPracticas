package org.example.servicesImpl;

import org.example.models.Carrito;
import org.example.models.ItemCarrito;
import org.example.models.Producto;
import org.example.services.CarritoService;
import org.example.services.ItemCarritoService;

import java.util.List;

public class CarritoServiceImpl implements CarritoService {
    private ItemCarritoService itemCarritoService;

    public CarritoServiceImpl() {
        this.itemCarritoService = new ItemCarritoServiceImpl();
    }

    @Override
    public void agregarProducto(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }
        itemCarritoService.agregarItem(new ItemCarrito(producto, cantidad));
    }

    @Override
    public void eliminarProducto(String productoId) {
        itemCarritoService.eliminarItem(productoId);
    }

    @Override
    public void modificarCantidad(String productoId, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }
        itemCarritoService.modificarCantidad(productoId, cantidad);
    }

    @Override
    public double calcularTotal() {
        double total = 0;
        for (ItemCarrito item : itemCarritoService.obtenerItems()) {
            total += item.getSubtotal();
        }
        return total;
    }

    @Override
    public List<ItemCarrito> obtenerItems() {
        return itemCarritoService.obtenerItems();
    }
}
