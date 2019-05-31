import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class Grafo implements Serializable {

    private ArrayList<Circulo> circulos = null;
    private Vector<Linea> Lineas;

    public Grafo(ArrayList<Circulo> circ, Vector<Linea> Lineas){
        this.circulos=circ;
        this.Lineas=Lineas;
    }

    public ArrayList<Circulo> getCirculos() {
        return circulos;
    }

    public void setCirculos(ArrayList<Circulo> circulos) {
        this.circulos = circulos;
    }

    public Vector<Linea> getLineas() {
        return Lineas;
    }

    public void setLineas(Vector<Linea> lineas) {
        Lineas = lineas;
    }
}
