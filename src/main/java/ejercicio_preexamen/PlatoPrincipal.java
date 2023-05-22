package ejercicio_preexamen;

class PlatoPrincipal extends Plato {
    private double recargo;

    public PlatoPrincipal(String nombre, double precio, double recargo) {
        super(nombre, precio);
        this.recargo = recargo;
    }

    public double getRecargo() {
        return recargo;
    }
}