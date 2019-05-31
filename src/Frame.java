import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Vector;
import java.io.*;

public class Frame extends JFrame implements MouseListener, MouseMotionListener {
    JFileChooser seleccionar = new JFileChooser();
    private Integer cumguardar;
    File archivo;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    private Grafo g1;
    private ArrayList<Circulo> circulos = null;
    private Vector<Linea> Lineas;
    private Point p1, p2, pm;
    private Circulo mover;
    private int iNodo;
    private Circulo c1;
    private JButton reinicio;
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
    private String Guardarencorto;
    private JButton Guardaencorto;
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
    private ImageIcon icondick = new ImageIcon("src/Imagenes/mapa.png");
    private ImageIcon iconeliminar = new ImageIcon("src/Imagenes/basura.png");
    private ImageIcon iconguardar = new ImageIcon("src/Imagenes/disco.png");
    private ImageIcon cur = new ImageIcon("src/Imagenes/cursor.png");
    private ImageIcon icono = new ImageIcon("src/Imagenes/meme.png");
    private JMenu Guardar;
    private JMenu importar;
    private JMenuItem impweb;
    private JMenuBar atajos;
    private JMenuItem Guardara;
    private JMenuItem Abrirar;


    public Frame(){
        super("Ventana");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(600,600);
        super.setLocationRelativeTo(null);
        super.setVisible(true);
        super.setLayout(new BorderLayout());
        this.repaint();
        this.Lineas = new Vector<>();
        setCirculos(new ArrayList<>());
        Cursor cursor;
        Toolkit t = Toolkit.getDefaultToolkit();
        cursor = t.createCustomCursor(cur.getImage(),new Point(1,1),"Cursor");
        setCursor(cursor);
        cumguardar = 0;
        atajos= new JMenuBar();
        importar = new JMenu("Importar");
        impweb = new JMenuItem("Importar grafo de pagina web");
        Guardar = new JMenu("Archivo");
        Abrirar = new JMenuItem("Abrir archivo");
        Guardara = new JMenuItem("Guardar archivo");
        Guardar.add(Abrirar);
        Guardar.add(Guardara);
        importar.add(impweb);
        atajos.add(Guardar);
        atajos.add(importar);
        setJMenuBar(atajos);
        pop = new JPopupMenu();
        poparista = new JPopupMenu();
        reinicio = new JButton();
        reinicio.setIcon(iconeliminar);
        reinicio.setIconTextGap(3);
        reinicio.setHorizontalAlignment(SwingConstants.CENTER);
        reinicio.setVerticalAlignment(SwingConstants.CENTER);
        reinicio.setHorizontalTextPosition(SwingConstants.RIGHT);
        reinicio.setVerticalTextPosition(SwingConstants.CENTER);
        reinicio.setBorder(null);
        reinicio.setContentAreaFilled(false);
        boton = new JButton();
        boton.setIcon(icondick);
        boton.setIconTextGap(4);
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        boton.setVerticalAlignment(SwingConstants.CENTER);
        boton.setHorizontalTextPosition(SwingConstants.RIGHT);
        boton.setVerticalTextPosition(SwingConstants.CENTER);
        boton.setBorder(null);
        boton.setContentAreaFilled(false);
        Guardaencorto = new JButton();
        Guardaencorto.setIcon(iconguardar);
        Guardaencorto.setIconTextGap(5);
        Guardaencorto.setHorizontalAlignment(SwingConstants.CENTER);
        Guardaencorto.setVerticalAlignment(SwingConstants.CENTER);
        Guardaencorto.setHorizontalTextPosition(SwingConstants.RIGHT);
        Guardaencorto.setVerticalTextPosition(SwingConstants.CENTER);
        Guardaencorto.setBorder(null);
        Guardaencorto.setContentAreaFilled(false);
        jp = new JPanel();
        jp.setLayout(new GridLayout(1,3,10,2));
        jp.add(boton);
        jp.add((reinicio));
        jp.add(Guardaencorto);
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
        JScrollPane scrollPane = new JScrollPane(etiquetas);
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
        sur.add(scrollPane);
        poparista.add(eliminararista);
        super.add(sur,BorderLayout.EAST);
        super.add(jp,BorderLayout.SOUTH);
        this.addMouseMotionListener(this);
        addMouseListener(this);
        impweb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"AAHHHH se la creyo profe jajaja", "Troll", JOptionPane.PLAIN_MESSAGE, icono);
            }
        });
        Guardaencorto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(cumguardar==0){
                    g1 = new Grafo(circulos,Lineas);
                    if(seleccionar.showDialog(null,"Guardar")==JFileChooser.APPROVE_OPTION){
                        archivo = seleccionar.getSelectedFile();
                        guardararchivo(archivo);
                        Guardarencorto =archivo.getAbsolutePath();
                        cumguardar++;
                    }
                }else{
                    guardararchivo(new File(Guardarencorto));
                    JOptionPane.showMessageDialog(null,"Se guardo adecuadamente");
                }
            }
        });
        Guardara.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                g1 = new Grafo(circulos,Lineas);
                if(seleccionar.showDialog(null,"Guardar")==JFileChooser.APPROVE_OPTION){
                    archivo = seleccionar.getSelectedFile();
                    guardararchivo(archivo);
                    Guardarencorto =archivo.getAbsolutePath();
                    cumguardar++;
                }
            }
        });
        Abrirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if((seleccionar.showDialog(null,"Abrir"))==JFileChooser.APPROVE_OPTION){
                    archivo = seleccionar.getSelectedFile();
                    if(archivo.canRead()){
                        abrirarchivo(archivo);
                    }
                }
                repaint();
            }
        });
        reinicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int a = JOptionPane.showConfirmDialog(null,"¿Seguro que quieres eliminar esto?");
                if(a==0) {
                    circulos.clear();
                    Lineas.clear();
                    etiquetas.setText("");
                    repaint();
                }
                else{
                    JOptionPane.showMessageDialog(null,"OK");
                }
            }
        });
        BotonCirculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int x = (int) (Math.random() * 400) + 50;
                int y = (int) (Math.random() * 400) + 50;
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
                int a=0;
                for(Circulo cir: circulos){
                    if(cir.isInicio()){
                    a++;
                    }
                    if(cir.isFinal()){
                        a++;
                    }
                }
                if(a==2) {
                    lineaa = true;
                    mensaje();
                }
                else{
                    mensajedicks();
                }
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
                int a=0;
                for(Circulo circ: circulos){
                    if(circ.isInicio()){
                        a++;
                    }
                    if(circ.isFinal()){
                        a++;
                    }
                }
                if(a==2) {
                    dickstra();
                    realizardick(Fin);
                    boton.isOpaque();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Error, necesitas definir nodo inicio y final");
                }

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

        for( Linea linea : Lineas){
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
            for(Linea linea : Lineas){
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
            for (Linea linea: Lineas){
                if(new Rectangle(linea.getX1()-30,linea.getY1()-30,Circulo.d,Circulo.d).contains(e.getPoint())){
                    pm = new Point(((e.getX()+linea.getX2())/2),((e.getY()+linea.getY2())/2));
                    this.Lineas.set(iE, new Linea(e.getX(),e.getY(),linea.getX2(),linea.getY2(),linea.getValor(),linea.getInicio(),linea.getFin(),linea.isDickar(),pm));
                }
                else if(new Rectangle(linea.getX2()-30,linea.getY2()-30,Circulo.d,Circulo.d).contains(e.getPoint())){
                    pm = new Point(((linea.getX1()+e.getX())/2),((linea.getY1()+e.getY())/2));
                    this.Lineas.set(iE, new Linea(linea.getX1(),linea.getY1(),e.getX(),e.getY(),linea.getValor(),linea.getInicio(),linea.getFin(),linea.isDickar(),pm));
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
                    if(!circulo.isInicio()) {
                        p2 = new Point(circulo.getX(), circulo.getY());
                        String valor = JOptionPane.showInputDialog("Ingresa el valor de la arista: ");
                        //System.out.println(circulo.getNombre());
                        //System.out.println(c1.getNombre());
                        if (valor != null) {
                            int valorn = Integer.parseInt(valor);
                            c1.agregar(circulo, valorn);
                            pm = puntomedarista(p1, p2);
                            Linea l1 = new Linea(p1.x, p1.y, p2.x, p2.y, valorn, c1, circulo, pm);

                            this.Lineas.add(l1);
                            l1.setInicio(c1);
                            l1.setFin(circulo);
                            c1.lineas.add(l1);
                            p1 = null;
                            p2 = null;
                            lineaa = false;
                            repaint();
                        } else {
                            p1 = null;
                            p2 = null;
                            lineaa = false;
                        }
                    }else {
                        JOptionPane.showMessageDialog(this,"No puedes regresar al nodo inicio");
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

    public void mensajedicks(){
        JOptionPane.showMessageDialog(this,"Tienes que ingresar el nodo inicio y final antes");
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
            for (Linea linea: Lineas){
                if(new Rectangle(linea.getX1()-30,linea.getY1()-30,Circulo.d,Circulo.d).contains(e.getPoint())){
                    Lineas.remove(linea);
                    eliminar(e,circulo);
                }
                else if(new Rectangle(linea.getX2()-30,linea.getY2()-30,Circulo.d,Circulo.d).contains(e.getPoint())){
                    Lineas.remove(linea);
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
                for(Linea li: Lineas){
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
        Lineas.remove(linea);
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
        for (Circulo circ : circulos){
            if(circ.getInicio()){
                circ.setInicio(false);
                circ.setDick(false);
            }
        }
        circulo.setInicio(true);
        First = circulo;
        repaint();
    }


    public void nodofinal(Circulo circulo){
        for (Circulo circ : circulos){
            if(circ.getFinal()){
                circ.setDick(false);
                circ.setFinal(false);
            }
        }
        circulo.setFinal(true);
        Fin = circulo;
        repaint();
    }

    public void cambiacolor(Circulo circulo){
        circulo.setColor(true);
        repaint();
    }

    public void guardararchivo(File archivo){
        try {
            oos = new ObjectOutputStream(new FileOutputStream(archivo));
            oos.writeObject(g1);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void abrirarchivo(File archivo){
        try {
            ois = new ObjectInputStream(new FileInputStream(archivo));
            try {
                g1 = (Grafo)ois.readObject();
                setCirculos(g1.getCirculos());
                setLineas(g1.getLineas());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Vector<Linea> getLineas() {
        return Lineas;
    }

    public void setLineas(Vector<Linea> lineas) {
        Lineas = lineas;
    }
}
