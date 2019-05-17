import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Vector;

public class Frame extends JFrame implements MouseListener, MouseMotionListener {
    ArrayList<Circulo> circulos = null;
    private Vector<Linea> vectorlinea;
    private Point p1, p2;
    private Circulo mover;
    private int iNodo;

    public Frame(){
        super("Ventana");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(600,600);
        super.setVisible(true);
        this.vectorlinea = new Vector<>();
        setCirculos(new ArrayList<>());
        this.addMouseMotionListener(this);
        addMouseListener(this);
    }

    public void paint(Graphics g){
        super.paint(g);
        for(Circulo Circulos: getCirculos()){
            Circulos.pintar(g);
        }
        for( Linea linea : vectorlinea){
            linea.pintar(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            String nombre = JOptionPane.showInputDialog("Ingresa el nombre del nodo");
            getCirculos().add(new Circulo(e.getX(),e.getY(),nombre));
            repaint();
        }
        if (e.getButton() == MouseEvent.BUTTON3){
            for(Circulo circulo : circulos){
                if(new Rectangle(circulo.getX()-30,circulo.getY()-30,circulo.getD(),circulo.getD()).contains(e.getPoint())){
                    if(p1==null){
                        p1 = new Point(circulo.getX(),circulo.getY());
                    }
                    else{
                        p2 = new Point(circulo.getX(),circulo.getY());
                        String valor = JOptionPane.showInputDialog("Ingresa el valor de la arista: ");
                        this.vectorlinea.add(new Linea(p1.x,p1.y,p2.x,p2.y,valor));
                        repaint();
                        p1=null;
                        p2=null;
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int iN = 0;
        for(Circulo circulo : circulos){
            if(new Rectangle(circulo.getX()-30,circulo.getY()-30,circulo.getD(),circulo.getD()).contains(e.getPoint())){
                  mover = circulo;
                  iNodo = iN;
                  break;
            }
            iN++;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mover = null;
        iNodo = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setCirculos(ArrayList<Circulo> circulos) {
        this.circulos = circulos;
    }

    public ArrayList<Circulo> getCirculos() {
        return circulos;
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if(( mover != null) && (vectorlinea.isEmpty())){
            this.circulos.set(iNodo, new Circulo(e.getX(),e.getY(),mover.getNombre()));
            int iE = 0;
            for (Linea linea: vectorlinea){
                if(new Rectangle(linea.getX1()-30,linea.getY1()-30,Circulo.d,Circulo.d).contains(e.getPoint())){
                    this.vectorlinea.set(iE, new Linea(e.getX(),e.getY(),linea.getX2(),linea.getY2(),linea.getValor()));

                }
                else if(new Rectangle(linea.getX2()-30,linea.getY2()-30,Circulo.d,Circulo.d).contains(e.getPoint())){
                    this.vectorlinea.set(iE, new Linea(linea.getX1(),linea.getY1(),e.getX(),e.getY(),linea.getValor()));
                }
                iE++;
            }
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
