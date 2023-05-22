package ejercicio_preexamen;

import java.util.ArrayList;

class Plato {
    private String nombre;
    private double precio;

    public Plato(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}

