package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<ItemCarrito> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public List<ItemCarrito> getItems() {
        return items;
    }
}
