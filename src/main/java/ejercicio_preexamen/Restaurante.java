package ejercicio_preexamen;

import java.util.Scanner;

public class Restaurante {
    public static void main(String[] args) {
        Pedido pedido = new Pedido();
        Scanner scanner = new Scanner(System.in);
        boolean finalizarPedido = false;

        PlatoPrincipal platoPrincipal1 = new PlatoPrincipal("Sopa", 4.5, 0.0);
        PlatoPrincipal platoPrincipal2 = new PlatoPrincipal("Ensaladilla Rusa", 4.5, 0.0);
        PlatoPrincipal platoPrincipal3 = new PlatoPrincipal("Pure", 4.5, 0.0);
        PlatoSecundario platoSecundario1 = new PlatoSecundario("Filetes empanados", 5.5);
        PlatoSecundario platoSecundario2 = new PlatoSecundario("Croquetas", 5.5);
        PlatoSecundario platoSecundario3 = new PlatoSecundario("Tortilla de patata", 5.5);

        System.out.println("Bienvenido al Restaurante");
        System.out.println("-------------------------");
        System.out.println("Platos disponibles:");
        System.out.println("1. " + platoPrincipal1.getNombre() + " - " + platoPrincipal1.getPrecio() + "€");
        System.out.println("2. " + platoPrincipal2.getNombre() + " - " + platoPrincipal2.getPrecio() + "€");
        System.out.println("3. " + platoPrincipal3.getNombre() + " - " + platoPrincipal3.getPrecio() + "€");
        System.out.println("4. " + platoSecundario1.getNombre() + " - " + platoSecundario1.getPrecio() + "€");
        System.out.println("5. " + platoSecundario2.getNombre() + " - " + platoSecundario2.getPrecio() + "€");
        System.out.println("6. " + platoSecundario3.getNombre() + " - " + platoSecundario3.getPrecio() + "€");

        while (!finalizarPedido) {
            System.out.println("Selecciona un plato (1-6) o ingresa 0 para finalizar el pedido:");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 0:
                    finalizarPedido = true;
                    break;
                case 1:
                    System.out.println("Indica la cantidad:");
                    int cantidadPlato1 = scanner.nextInt();
                    pedido.agregarPlato(platoPrincipal1, cantidadPlato1);
                    break;
                case 2:
                    System.out.println("Indica la cantidad:");
                    int cantidadPlato2 = scanner.nextInt();
                    pedido.agregarPlato(platoPrincipal2, cantidadPlato2);
                    break;
                case 3:
                    System.out.println("Indica la cantidad:");
                    int cantidadPlato3 = scanner.nextInt();
                    pedido.agregarPlato(platoPrincipal3, cantidadPlato3);
                    break;
                case 4:
                    System.out.println("Indica la cantidad:");
                    int cantidadPlato4 = scanner.nextInt();
                    pedido.agregarPlato(platoSecundario1, cantidadPlato4);
                    break;
                case 5:
                    System.out.println("Indica la cantidad:");
                    int cantidadPlato5 = scanner.nextInt();
                    pedido.agregarPlato(platoSecundario2, cantidadPlato5);
                    break;
                case 6:
                    System.out.println("Indica la cantidad:");
                    int cantidadPlato6 = scanner.nextInt();
                    pedido.agregarPlato(platoSecundario3, cantidadPlato6);
                    break;
                default:
                    System.out.println("Opción inválida. Intenta nuevamente.");
            }
        }

        System.out.println("Procesando pedido...");
        pedido.generarDocumento();

        scanner.close();
    }
}