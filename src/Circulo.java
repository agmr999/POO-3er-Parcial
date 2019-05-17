import java.awt.*;

public class Circulo {

    private int x=0, y=0;
    private String nombre;
    static final int d=60;

    public Circulo(int x, int y, String nombre){
            this.x=x;
            this.y=y;
            this.nombre=nombre;
    }

    public void pintar(Graphics g){
        //g.drawOval(this.x,this.y,this.d,this.d);
        Graphics2D circulo = (Graphics2D)g;
        circulo.setStroke(new BasicStroke(3.f));
        circulo.setPaint(Color.blue);
        circulo.drawOval(this.x- d/2,this.y -d/2,d,d);
        g.drawString(nombre,x,y);
    }

    public int getY() {
        return y;
    }

    public int getD() {
        return d;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
