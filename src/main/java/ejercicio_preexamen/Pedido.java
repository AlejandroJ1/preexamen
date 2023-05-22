package ejercicio_preexamen;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Pedido {
    private Map<Plato, Integer> platos;

    public Pedido() {
        platos = new HashMap<>();
    }

    public void agregarPlato(Plato plato, int cantidad) {
        platos.put(plato, cantidad);
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
        try (PrintWriter writer = new PrintWriter(new FileWriter("pedido.txt"))) {
            writer.println("Detalles del pedido:");
            writer.println("---------------------");

            for (Plato plato : platos.keySet()) {
                int cantidad = platos.get(plato);
                double precio = plato.getPrecio()
                        + (plato instanceof PlatoPrincipal ? ((PlatoPrincipal) plato).getRecargo() : 0);
                double subtotalPlato = precio * cantidad;
                writer.println("- " + plato.getNombre() + " x " + cantidad + " = " + subtotalPlato + "€");
            }

            writer.println("---------------------");
            writer.println("Subtotal: " + subtotal + "€");
            writer.println("Descuento: " + descuento + "€");
            writer.println("Total a pagar: " + total + "€");

            System.out.println("Documento generado exitosamente.");

        } catch (IOException e) {
            System.out.println("Error al generar el documento: " + e.getMessage());
        }
    }

}
