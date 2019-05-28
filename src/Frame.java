import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Vector;

public class Frame extends JFrame implements MouseListener, MouseMotionListener {
    private ArrayList<Circulo> circulos = null;
    private Vector<Linea> vectorlinea;
    private ArrayList<Integer> Distancia = new ArrayList<>();
    private ArrayList<Circulo> tiene = new ArrayList<>();
    private Point p1, p2, pm;
    private Circulo mover;
    private int iNodo;
    private Circulo c1;
    private JPopupMenu pop;
    private  JMenuItem conectar;
    private  JMenuItem color;
    private  JMenuItem nombre;
    private  JMenuItem inicio;
    private  JMenuItem fin;
    private  JMenuItem eliminar;
    private  JMenuItem Cambiararista;
    private  JMenuItem eliminararista;
    private JPopupMenu poparista;
    private Circulo usarcirculo;
    private Linea usararista;
    private MouseEvent ee;
    private JPanel jp;
    private JButton boton = new JButton("Dikstra");
    private Circulo First;
    private Circulo Fin;


    public Frame(){
        super("Ventana");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(600,600);
        super.setVisible(true);
        super.setLayout(new BorderLayout());
        this.vectorlinea = new Vector<>();
        setCirculos(new ArrayList<>());
        pop = new JPopupMenu();
        poparista = new JPopupMenu();
        jp = new JPanel();
        jp.add(boton);
        conectar = new JMenuItem("Conectar");
        color = new JMenuItem("Cambiar color");
        nombre = new JMenuItem("Cambiar Nombre");
        inicio = new JMenuItem("Nodo inicio");
        fin = new JMenuItem("Nodo final");
        eliminar = new JMenuItem("Eliminar nodo");
        eliminararista = new JMenuItem("Eliminar arista");
        Cambiararista = new JMenuItem("Cambiar valor de la arista");
        pop.add(conectar);
        pop.add(color);
        pop.add(eliminar);
        pop.add(nombre);
        pop.add(inicio);
        pop.add(fin);
        poparista.add(eliminararista);
        super.add(jp,BorderLayout.SOUTH);
        this.addMouseMotionListener(this);
        addMouseListener(this);
        conectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                lineas(ee, usarcirculo);
                repaint();
            }
        });
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                eliminar(ee,usarcirculo);
                repaint();
            }
        });
        inicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nodoinicio(usarcirculo);
                repaint();
            }
        });
        fin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nodofinal(usarcirculo);
                repaint();
            }
        });
        eliminararista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                eliminararis(usararista);
            }
        });
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dickstra();
                realizardick(Fin);
            }
        });
    }

    public void paint(Graphics g){
        super.paint(g);
        for(Circulo Circulos: getCirculos()){
            Circulos.pintar(g);
            if(Circulos.getInicio()){
                Circulos.reinicio(g);
            }
            if(Circulos.getFinal()){
                Circulos.refinal(g);
            }
            if(Circulos.isDick()){
                Circulos.dickstra(g);
            }
        }

        for( Linea linea : vectorlinea){
            if(linea.isDickar()){
                linea.dickpin(g);
            }
            else {
                linea.pintar(g);
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            String nombre = JOptionPane.showInputDialog("Ingresa el nombre del nodo");
            if(nombre!=null) {
                getCirculos().add(new Circulo(e.getX(), e.getY(), nombre));
                repaint();
            }
        }
        if (e.getButton() == MouseEvent.BUTTON3){
            for(Circulo circulo : circulos){
                if(new Rectangle(circulo.getX()-30,circulo.getY()-30,circulo.getD(),circulo.getD()).contains(e.getPoint())) {
                    pop.show(e.getComponent(), e.getX(), e.getY());
                    usarcirculo = circulo;
                    ee = e;
                }
            }
            for(Linea linea : vectorlinea){
                if(new Rectangle(linea.getPm().x,linea.getPm().y,20,20).contains(e.getPoint())){
                    poparista.show(e.getComponent(), e.getX(), e.getY());
                    usararista = linea;
                }
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int iN = 0;
        for(Circulo circulo : circulos){
            if(new Rectangle(circulo.getX()-30,circulo.getY()-30,circulo.getD(),circulo.getD()).contains(e.getPoint())){
                  mover = circulo;
                  iNodo = iN;
               // System.out.println(iN);
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

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public ArrayList<Circulo> getCirculos() {
        return circulos;
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if(( mover != null)){
            this.circulos.set(iNodo, new Circulo(e.getX(),e.getY(),mover.getNombre(),mover.getTiene(),mover.getDistancia(),mover.getInicio(),mover.getFinal(),mover.isDick()));
            int iE = 0;
            for (Linea linea: vectorlinea){
                if(new Rectangle(linea.getX1()-30,linea.getY1()-30,Circulo.d,Circulo.d).contains(e.getPoint())){
                    this.vectorlinea.set(iE, new Linea(e.getX(),e.getY(),linea.getX2(),linea.getY2(),linea.getValor(),linea.getInicio(),linea.getFin(),linea.isDickar()));

                }
                else if(new Rectangle(linea.getX2()-30,linea.getY2()-30,Circulo.d,Circulo.d).contains(e.getPoint())){
                    this.vectorlinea.set(iE, new Linea(linea.getX1(),linea.getY1(),e.getX(),e.getY(),linea.getValor(),linea.getInicio(),linea.getFin(),linea.isDickar()));
                }
                iE++;
            }
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    public void lineas(MouseEvent e, Circulo circulo){
                if(p1==null){
                    JOptionPane.showMessageDialog(this,"Selecciona otro para hacer la conexion");
                    p1 = new Point(circulo.getX(),circulo.getY());
                    c1 = circulo;
                        circulo.mostrar();
                }
                else{
                    p2 = new Point(circulo.getX(),circulo.getY());
                    String valor = JOptionPane.showInputDialog("Ingresa el valor de la arista: ");
                    //System.out.println(circulo.getNombre());
                    //System.out.println(c1.getNombre());
                    if(valor!=null) {
                        int valorn = Integer.parseInt(valor);
                        c1.agregar(circulo, valorn);
                        pm = puntomedarista(p1,p2);
                        Linea l1 = new Linea(p1.x, p1.y, p2.x, p2.y, valorn, c1,circulo, pm);

                        this.vectorlinea.add(l1);
                        l1.setInicio(c1);
                        l1.setFin(circulo);
                        c1.lineas.add(l1);
                        p1=null;
                        p2=null;
                        repaint();
                    }
                    else{
                        p1=null;
                        p2=null;
                    }
        }
    }


    public void eliminar(MouseEvent e,Circulo circulo){
        if(circulo!=null) {
            for( Circulo circ : circulos) {
                circ.eliminararista(circulo.getNombre());
            }
            circulos.remove(circulo);
        }
        try {
            for (Linea linea: vectorlinea){
                if(new Rectangle(linea.getX1()-30,linea.getY1()-30,Circulo.d,Circulo.d).contains(e.getPoint())){
                    vectorlinea.remove(linea);
                    eliminar(e,circulo);
                }
                else if(new Rectangle(linea.getX2()-30,linea.getY2()-30,Circulo.d,Circulo.d).contains(e.getPoint())){
                    vectorlinea.remove(linea);
                    eliminar(e,circulo);
                }
            }
            repaint();
        }catch (ConcurrentModificationException ex){
                eliminar(e,circulo);
        }

    }

    public void dickstra(){
        Integer a = First.recorrer(0,"A");
        /*for(Circulo circ : circulos){
            System.out.println("----------------");
            System.out.println(circ.getNombre());
            circ.mostraretiqueta();
        }*/
    }

    public void realizardick(Circulo dick){
    if(!dick.getInicio()){
        /*if(dick.getFinal()) {
            System.out.println("Entro");
            dick.setFinal(false);
        }*/
        dick.setDick(true);
        Etiqueta et= dick.sacardick();
        for(Circulo circ2: circulos){
            if(circ2.getNombre()==et.getNodo()){
                for(Linea li: vectorlinea){
                    if((li.getInicio()==circ2)&&(li.getFin()==dick)){
                        li.setDickar(true);
                    }
                }
            }
        }
        System.out.println("si "+et.getNodo());
        for (Circulo circul: circulos){
            if(et.getNodo()==circul.getNombre()){
                //System.out.println("si "+et.getNodo());
                realizardick(circul);
            }
        }
    }else {
        //dick.setInicio(false);
        dick.setDick(true);
        System.out.println("ya");
        repaint();
    }
    }

    public void cambiarista(Linea linea){
        String valor = JOptionPane.showInputDialog("Ingresa el valor de la arista: ");
        if(valor!=null) {
            int valorn = Integer.parseInt(valor);
            linea.setValor(valorn);
        }else {
            System.out.println("ok");
        }
    }

    public void eliminararis(Linea linea){
        vectorlinea.remove(linea);
        linea.getInicio().eliminararista(linea.getFin().getNombre());
        repaint();
    }

    public Point puntomedarista(Point a, Point b){
        int x = ((a.x+b.x)/2);
        int y = ((a.y+b.y)/2);
        Point p = new Point(x,y);
        return p;
    }


    public void nodoinicio(Circulo circulo){
        circulo.setInicio(true);
        First = circulo;
    }


    public void nodofinal(Circulo circulo){
        circulo.setFinal(true);
        Fin = circulo;
    }

}
