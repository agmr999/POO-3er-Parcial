import java.awt.*;

public class Linea {
    private int x1, y1, x2, y2;
    private int valor;
    private Circulo inicio;
    private Circulo fin;
    private boolean dickar = false;
    private Point pm;

    public Linea(int x1, int y1, int x2, int y2, int valor, Circulo circulo, Circulo fin, Point pm){
            this.x1=x1;
            this.y1=y1;
            this.x2=x2;
            this.y2=y2;
            this.valor=valor;
            this.inicio = circulo;
            this.fin=fin;
            this.pm=pm;
        }

    public Linea(int x1, int y1, int x2, int y2, int valor, Circulo circulo, Circulo fin, boolean dick){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.valor=valor;
        this.inicio = circulo;
        this.fin=fin;
        this.dickar=dick;
    }

        public void pintar(Graphics g){
            Graphics2D linea = (Graphics2D)g;
            linea.setStroke(new BasicStroke(2.f));
            linea.setPaint(Color.BLACK);
            linea.drawLine(x1,y1,x2,y2);
            if(x1>x2 && y1>y2){
                g.drawString(String.valueOf(valor),x1 - Math.abs((x1-x2)/2),y1 - Math.abs((y1-y2)/2));
            }
            if(x1<x2 && y1<y2){
                g.drawString(String.valueOf(valor),x2 - Math.abs((x1-x2)/2),y2 - Math.abs((y1-y2)/2));
            }
            if(x1>x2 && y1<y2){
                g.drawString(String.valueOf(valor),x1 - Math.abs((x1-x2)/2),y2 - Math.abs((y1-y2)/2));
            }
            if(x1<x2 && y1>y2){
                g.drawString(String.valueOf(valor),x2 - Math.abs((x1-x2)/2),y1 - Math.abs((y1-y2)/2));
            }
        }

    public void dickpin(Graphics g){
        Graphics2D linea = (Graphics2D)g;
        linea.setStroke(new BasicStroke(4.f));
        linea.setPaint(Color.RED);
        linea.drawLine(x1,y1,x2,y2);
        if(x1>x2 && y1>y2){
            g.drawString(String.valueOf(valor),x1 - Math.abs((x1-x2)/2),y1 - Math.abs((y1-y2)/2));
        }
        if(x1<x2 && y1<y2){
            g.drawString(String.valueOf(valor),x2 - Math.abs((x1-x2)/2),y2 - Math.abs((y1-y2)/2));
        }
        if(x1>x2 && y1<y2){
            g.drawString(String.valueOf(valor),x1 - Math.abs((x1-x2)/2),y2 - Math.abs((y1-y2)/2));
        }
        if(x1<x2 && y1>y2){
            g.drawString(String.valueOf(valor),x2 - Math.abs((x1-x2)/2),y1 - Math.abs((y1-y2)/2));
        }
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Circulo getInicio() {
        return inicio;
    }

    public void setInicio(Circulo inicio) {
        this.inicio = inicio;
    }

    public Circulo getFin() {
        return fin;
    }

    public void setFin(Circulo fin) {
        this.fin = fin;
    }

    public boolean isDickar() {
        return dickar;
    }

    public void setDickar(boolean dickar) {
        this.dickar = dickar;
    }

    public Point getPm() {
        return pm;
    }

    public void setPm(Point pm) {
        this.pm = pm;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
