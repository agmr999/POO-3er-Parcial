import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Vector;

public class Frame extends JFrame implements MouseListener, MouseMotionListener {
    private ArrayList<Circulo> circulos = null;
    private Vector<Linea> Ĺineas;
    private Point p1, p2, pm;
    private Circulo mover;
    private int iNodo;
    private Circulo c1;
    private JPopupMenu pop;
    private  JMenuItem color;
    private  JMenuItem inicio;
    private  JMenuItem fin;
    private  JMenuItem eliminar;
    private  JMenuItem Cambiararista;
    private  JMenuItem eliminararista;
    private JPopupMenu poprincipal;
    private JMenuItem Ingresacirc;
    private JMenuItem Ingresaaris;
    private JPopupMenu poparista;
    private Circulo usarcirculo;
    private Linea usararista;
    private MouseEvent ee;
    private JPanel jp;
    private JButton boton = new JButton("Dikstra");
    private Circulo First;
    private Circulo Fin;
    private boolean lineaa = false;
    private JButton BotonCirculo;
    private JButton Botonarista;
    private JPanel sur;
    public static JTextArea etiquetas;
    private ImageIcon iconobtn = new ImageIcon("src/Imagenes/circulo.png");
    private ImageIcon iconoaris = new ImageIcon("src/Imagenes/segmento.png");


    public Frame(){
        super("Ventana");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(600,600);
        super.setVisible(true);
        super.setLayout(new BorderLayout());
        this.Ĺineas = new Vector<>();
        setCirculos(new ArrayList<>());
        pop = new JPopupMenu();
        poparista = new JPopupMenu();
        jp = new JPanel();
        jp.add(boton);
        BotonCirculo = new JButton();
        BotonCirculo.setIcon(iconobtn);
        BotonCirculo.setIconTextGap(2);
        BotonCirculo.setHorizontalAlignment(SwingConstants.CENTER);
        BotonCirculo.setVerticalAlignment(SwingConstants.CENTER);
        BotonCirculo.setHorizontalTextPosition(SwingConstants.RIGHT);
        BotonCirculo.setVerticalTextPosition(SwingConstants.CENTER);
        BotonCirculo.setBorder(null);
        BotonCirculo.setContentAreaFilled(false);
        Botonarista = new JButton();
        Botonarista.setIcon(iconoaris);
        Botonarista.setBorder(null);
        Botonarista.setContentAreaFilled(false);
        Botonarista.setIconTextGap(1);
        Botonarista.setHorizontalAlignment(SwingConstants.CENTER);
        Botonarista.setVerticalAlignment(SwingConstants.CENTER);
        Botonarista.setHorizontalTextPosition(SwingConstants.RIGHT);
        Botonarista.setVerticalTextPosition(SwingConstants.CENTER);
        sur = new JPanel();
        sur.setLayout(new GridLayout(3,1,2,20));
        etiquetas=new JTextArea(4,8);
        etiquetas.setBorder(BorderFactory.createTitledBorder("Etiquetas"));
        color = new JMenuItem("Cambiar color");
        inicio = new JMenuItem("Nodo inicio");
        fin = new JMenuItem("Nodo final");
        eliminar = new JMenuItem("Eliminar nodo");
        eliminararista = new JMenuItem("Eliminar arista");
        Cambiararista = new JMenuItem("Cambiar valor de la arista");
        Ingresacirc = new JMenuItem("Ingresa un nodo");
        Ingresaaris = new JMenuItem("Ingresa Arista");
        sur.setBorder(new BevelBorder(BevelBorder.LOWERED));
        jp.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        poprincipal = new JPopupMenu();
        poprincipal.add(Ingresacirc);
        poprincipal.add(Ingresaaris);
        pop.add(color);
        pop.add(eliminar);
        pop.add(inicio);
        pop.add(fin);
        sur.add(BotonCirculo);
        sur.add(Botonarista);
        sur.add(etiquetas);
        poparista.add(eliminararista);
        super.add(sur,BorderLayout.EAST);
        super.add(jp,BorderLayout.SOUTH);
        this.addMouseMotionListener(this);
        addMouseListener(this);
        BotonCirculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int x = (int) (Math.random() * 450) + 50;
                int y = (int) (Math.random() * 450) + 50;
                botoncirculo(x,y);
            }
        });
        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cambiacolor(usarcirculo);
            }
        });
        Botonarista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                lineaa = true;
                mensaje();
            }
        });
        Ingresacirc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ingresacirc(ee);
            }
        });
        Ingresaaris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                lineaa = true;
                mensaje();
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
                boton.isOpaque();
            }
        });
    }

    public void paint(Graphics g){
        super.paint(g);
        for(Circulo Circulos: getCirculos()){
            if(Circulos.isColor()){
                Circulos.color(g);
            }
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

        for( Linea linea : Ĺineas){
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
            if(lineaa){
                for (Circulo circulo : circulos) {
                    if(new Rectangle(circulo.getX()-30,circulo.getY()-30,circulo.getD(),circulo.getD()).contains(e.getPoint())) {
                        lineas(e,circulo);
                    }
                }
            }
        }
        if (e.getButton() == MouseEvent.BUTTON3){
            int Cir =0, Lin = 0;
            for(Circulo circulo : circulos){
                if(new Rectangle(circulo.getX()-30,circulo.getY()-30,circulo.getD(),circulo.getD()).contains(e.getPoint())) {
                    pop.show(e.getComponent(), e.getX(), e.getY());
                    usarcirculo = circulo;
                    ee = e;
                    Cir =1;
                }
            }
            for(Linea linea : Ĺineas){
                if(new Rectangle(linea.getPm().x,linea.getPm().y,10,10).contains(e.getPoint())){
                    poparista.show(e.getComponent(), e.getX(), e.getY());
                    usararista = linea;
                    Lin = 1;
                }
            }
            if(Lin==0&&Cir==0){
                ee = e;
                poprincipal.show(e.getComponent(), e.getX(), e.getY());
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
            for (Linea linea: Ĺineas){
                if(new Rectangle(linea.getX1()-30,linea.getY1()-30,Circulo.d,Circulo.d).contains(e.getPoint())){
                    pm = new Point(((e.getX()+linea.getX2())/2),((e.getY()+linea.getY2())/2));
                    this.Ĺineas.set(iE, new Linea(e.getX(),e.getY(),linea.getX2(),linea.getY2(),linea.getValor(),linea.getInicio(),linea.getFin(),linea.isDickar(),pm));

                }
                else if(new Rectangle(linea.getX2()-30,linea.getY2()-30,Circulo.d,Circulo.d).contains(e.getPoint())){
                    pm = new Point(((linea.getX1()+e.getX())/2),((linea.getY1()+e.getY())/2));
                    this.Ĺineas.set(iE, new Linea(linea.getX1(),linea.getY1(),e.getX(),e.getY(),linea.getValor(),linea.getInicio(),linea.getFin(),linea.isDickar(),pm));
                }
                iE++;
            }
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    public void mensaje(){
        JOptionPane.showMessageDialog(this,"Selecciona dos nodos para enlazarlos");
    }

    public void lineas(MouseEvent e, Circulo circulo){
                if(p1==null){
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

                        this.Ĺineas.add(l1);
                        l1.setInicio(c1);
                        l1.setFin(circulo);
                        c1.lineas.add(l1);
                        p1=null;
                        p2=null;
                        lineaa = false;
                        repaint();
                    }
                    else{
                        p1=null;
                        p2=null;
                        lineaa = false;
                    }
        }
    }

    public void ingresacirc(MouseEvent e){
        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del nodo");
        if(nombre!=null) {
            getCirculos().add(new Circulo(e.getX(), e.getY(), nombre));
            repaint();
        }
    }

    public void botoncirculo(int x, int y){
        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del nodo");
        if(nombre!=null) {
            getCirculos().add(new Circulo(x, y, nombre));
            repaint();
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
            for (Linea linea: Ĺineas){
                if(new Rectangle(linea.getX1()-30,linea.getY1()-30,Circulo.d,Circulo.d).contains(e.getPoint())){
                    Ĺineas.remove(linea);
                    eliminar(e,circulo);
                }
                else if(new Rectangle(linea.getX2()-30,linea.getY2()-30,Circulo.d,Circulo.d).contains(e.getPoint())){
                    Ĺineas.remove(linea);
                    eliminar(e,circulo);
                }
            }
            repaint();
        }catch (ConcurrentModificationException ex){
                eliminar(e,circulo);
        }

    }

    public void dickstra(){
        int a = First.recorrer(0," ");
        etiquetas.setLineWrap(true);
        //String cadena = new String("Mi texto con\nun salto de línea");
        //etiquetas.setText("<html><body>JLabel con <br> varias <br>linea :-) </body></html>");
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
                for(Linea li: Ĺineas){
                    if((li.getInicio()==circ2)&&(li.getFin()==dick)){
                        li.setDickar(true);
                    }
                }
            }
        }
        System.out.println("si "+et.getNodo()+" "+et.getValor());
        for (Circulo circul: circulos){
            if(et.getNodo()==circul.getNombre()){
                //System.out.println("si "+et.getNodo());
                realizardick(circul);
            }
        }
    }else {
        //dick.setInicio(false);
        dick.setDick(true);
        //System.out.println("ya");
        repaint();
    }
    }

    /*public void cambiarista(Linea linea){
        String valor = JOptionPane.showInputDialog("Ingresa el valor de la arista: ");
        if(valor!=null) {
            int valorn = Integer.parseInt(valor);
            linea.setValor(valorn);
        }else {
            System.out.println("ok");
        }
    }*/

    public void eliminararis(Linea linea){
        Ĺineas.remove(linea);
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

    public void cambiacolor(Circulo circulo){
        circulo.setColor(true);
        repaint();
    }

}
