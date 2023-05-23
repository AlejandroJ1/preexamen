package ejercicio_preexamen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


class Pedido {
    private Map<Plato, Integer> platos;

    public Pedido() {
        platos = new HashMap<>();
    }

    public void agregarPlato(Plato plato, int cantidad) {
        if (platos.containsKey(plato)) {
            int cantidadExistente = platos.get(plato);
            platos.put(plato, cantidadExistente + cantidad);
        } else {
            platos.put(plato, cantidad);
        }
    }

    public List<Plato> getPlatos() {
        return new ArrayList<>(platos.keySet());
    }

    public int getCantidadPlato(Plato plato) {
        return platos.getOrDefault(plato, 0);
    }

    public void generarDocumento() {
        double subtotal = 0;
        System.out.println("Detalles del pedido:");
        System.out.println("---------------------");

        for (Plato plato : platos.keySet()) {
            int cantidad = platos.get(plato);
            double precio = plato.getPrecio()
                    + (plato instanceof PlatoPrincipal ? ((PlatoPrincipal) plato).getRecargo() : 0);
            double subtotalPlato = precio * cantidad;
            subtotal += subtotalPlato;
            System.out.println("- " + plato.getNombre() + " x " + cantidad + " = " + subtotalPlato + "€");
        }

        double descuento = calcularDescuento();
        double total = subtotal - descuento;

        System.out.println("---------------------");
        System.out.println("Subtotal: " + subtotal + "€");
        System.out.println("Descuento: " + descuento + "€");
        System.out.println("Total a pagar: " + total + "€");

        generarDocumentoTexto(subtotal, descuento, total);
    }

    private double calcularDescuento() {
        int cantidadCombinaciones = 0;

        int cantidadPrimeros = getCantidadPlatoTipo(PlatoPrincipal.class);
        int cantidadSegundos = getCantidadPlatoTipo(PlatoSecundario.class);

        if (cantidadPrimeros > 0 && cantidadSegundos > 0) {
            cantidadCombinaciones = Math.min(cantidadPrimeros, cantidadSegundos);
        }

        return cantidadCombinaciones * 1.0;
    }

    private int getCantidadPlatoTipo(Class<? extends Plato> tipoPlato) {
        int cantidad = 0;

        for (Plato plato : platos.keySet()) {
            if (tipoPlato.isInstance(plato)) {
                cantidad += platos.get(plato);
            }
        }

        return cantidad;
    }

    private void generarDocumentoTexto(double subtotal, double descuento, double total) {
        File archivo = new File("pedido.txt");
        try {
            if (archivo.createNewFile()) {
                System.out.println("Se ha creado el archivo " + archivo.getName());
            } else {
                System.out.println("El archivo ya existe");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo");
        }
        try {
            FileWriter escribir_precios = new FileWriter(archivo, true);

            escribir_precios.write("Subtotal: " + subtotal + "€\n");
            escribir_precios.write("Descuento: " + descuento + "€\n");
            escribir_precios.write("Total a pagar: " + total + "€\n");
            escribir_precios.close();

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo");
            e.printStackTrace();
        }

        try {
            Scanner lector_pedido = new Scanner(archivo);
            while (lector_pedido.hasNextLine()) {
                String linea = lector_pedido.nextLine();
                System.out.println(linea);
            }
            lector_pedido.close();

        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();

        }
    }
}