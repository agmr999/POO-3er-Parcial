import java.io.Serializable;

public class Etiqueta implements Serializable {

    private String nodo;
    private Integer valor;

    public Etiqueta(String nodo, Integer valor){
        this.nodo=nodo;
        this.valor=valor;
    }

    public String getNodo() {
        return nodo;
    }

    public void setNodo(String nodo) {
        this.nodo = nodo;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
