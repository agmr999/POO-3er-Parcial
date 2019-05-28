import java.awt.*;
import java.util.ArrayList;

public class Circulo {

    private int x=0, y=0;
    private String nombre;
    static final int d=60;
    private ArrayList<Integer> Distancia = new ArrayList<>();
    private ArrayList<Circulo> tiene = new ArrayList<>();
    private boolean Inicio = false;
    private boolean Final = false;
    private boolean color = false;
    private ArrayList<Etiqueta> etiquetas = new ArrayList<>();
    public ArrayList<Linea> lineas = new ArrayList<>();
    private boolean Dick = false;

    public Circulo(int x, int y, String nombre){
        this.x=x;
        this.y=y;
        this.nombre=nombre;
    }


    public Circulo(int x, int y, String nombre, ArrayList<Circulo> tiene, ArrayList<Integer> distancia, boolean Inicio, boolean Fin, boolean dick){
            this.x=x;
            this.y=y;
            this.nombre=nombre;
            this.Inicio=Inicio;
            this.Final=Fin;
            this.setTiene(tiene);
            this.setDistancia(distancia);
            this.Dick=dick;
    }

    public void color(Graphics g){
        //g.drawOval(this.x,this.y,this.d,this.d);
        Graphics2D circulo = (Graphics2D)g;
        circulo.setStroke(new BasicStroke(2.f));
        int x = (int) (Math.random() * 1000) + 1;
        int y = (int) (Math.random() * 1000) + 1;
        int z = (int) (Math.random() * 1000) + 1;
        circulo.setPaint(Color.getHSBColor(x,y,z));
        //circulo.fillOval(this.x- d/2,this.y -d/2,d,d);
        circulo.fillOval(this.x- d/2,this.y -d/2,d,d);
        g.drawString(nombre,x,y);
    }


    public void dickstra(Graphics g){
        //g.drawOval(this.x,this.y,this.d,this.d);
        Graphics2D circulo = (Graphics2D)g;
        circulo.setStroke(new BasicStroke(3.f));
        circulo.setPaint(Color.RED);
        //circulo.fillOval(this.x- d/2,this.y -d/2,d,d);
        circulo.fillOval(this.x- d/2,this.y -d/2,d,d);
        g.drawString(nombre,x,y);
    }


    public void refinal(Graphics g){
        //g.drawOval(this.x,this.y,this.d,this.d);
        Graphics2D circulo = (Graphics2D)g;
        circulo.setStroke(new BasicStroke(3.f));
        circulo.setPaint(Color.BLACK);
        circulo.fillOval(this.x- d/2,this.y -d/2,d,d);
        //circulo.drawOval(this.x- d/2,this.y -d/2,d,d);
        g.drawString(nombre,x,y);
    }

    public void reinicio(Graphics g){
        //g.drawOval(this.x,this.y,this.d,this.d);
        Graphics2D circulo = (Graphics2D)g;
        circulo.setStroke(new BasicStroke(3.f));
        circulo.setPaint(Color.BLUE);
        circulo.fillOval(this.x- d/2,this.y -d/2,d,d);
        //circulo.drawOval(this.x- d/2,this.y -d/2,d,d);
        g.drawString(nombre,x,y);
    }

    public void pintar(Graphics g){
        //g.drawOval(this.x,this.y,this.d,this.d);
        Graphics2D circulo = (Graphics2D)g;
        circulo.setStroke(new BasicStroke(3.f));
        circulo.setPaint(Color.BLACK);
        //circulo.fillOval(this.x- d/2,this.y -d/2,d,d);
        circulo.drawOval(this.x- d/2,this.y -d/2,d,d);
        g.drawString(nombre,x-4,y-10);
    }

    public void agregar(Circulo circulo, int valor){
        tiene.add(circulo);
        Distancia.add(valor);
    }

    public void eliminararista(String nombre){
        int a=0;
        for (Circulo circ: tiene){
            if(circ.getNombre()==nombre){
                tiene.remove(circ);
                Distancia.remove(a);
                break;
            }
            a++;
        }
    }

    public Integer recorrer(Integer valor, String nombre){
        String text = "";
        int a=0;
        Etiqueta et = new Etiqueta(nombre,valor);
        etiquetas.add(et);
        if(!this.getTiene().isEmpty()) {
            for (Circulo circulo : tiene) {
                circulo.recorrer(this.Distancia.get(a)+valor,this.getNombre());
                a++;
            }
        }
        else{
            //System.out.println("si");
        }
        //System.out.println(this.getNombre()+"---["+nombre+","+valor+"]");
        text = text + (this.getNombre()+"---["+nombre+","+valor+"]");
        Frame.etiquetas.setText(Frame.etiquetas.getText()+"\n"+text);
        return a;
    }

    public Etiqueta sacardick(){
        Integer x = 100000000;
        Etiqueta eti = new Etiqueta(this.getNombre(),1000000000);
            for (Etiqueta et : etiquetas) {
                if (et.getValor() < x) {
                    x = et.getValor();
                    eti = new Etiqueta(et.getNodo(),et.getValor());
            }
        }
        //System.out.println("{"+eti.getNodo()+","+eti.getValor()+"}");
            return eti;
    }

    public void mostraretiqueta(){
        for ( Etiqueta et: etiquetas){
            System.out.println("["+et.getNodo()+","+et.getValor()+"]");
        }
    }

    public void mostrar(){
        int a=0;
            for (Circulo circulo : tiene) {
                System.out.print(circulo.getNombre()+"-");
                System.out.println(Distancia.get(a));
                a++;
        }
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

    public ArrayList<Integer> getDistancia() {
        return Distancia;
    }

    public void setDistancia(ArrayList<Integer> distancia) {
        Distancia = distancia;
    }

    public ArrayList<Circulo> getTiene() {
        return tiene;
    }

    public void setTiene(ArrayList<Circulo> tiene) {
        this.tiene = tiene;
    }

    public boolean isInicio() {
        return Inicio;
    }

    public void setInicio(boolean inicio) {
        Inicio = inicio;
    }

    public boolean isFinal() {
        return Final;
    }

    public void setFinal(boolean aFinal) {
        Final = aFinal;
    }

    public boolean getFinal(){
        return this.Final;
    }


    public boolean getInicio(){
        return this.Inicio;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public ArrayList<Linea> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<Linea> lineas) {
        this.lineas = lineas;
    }

    public boolean isDick() {
        return Dick;
    }

    public void setDick(boolean dick) {
        Dick = dick;
    }
}