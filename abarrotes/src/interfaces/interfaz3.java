package interfaces;


import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexion.Conexion1;

import App.App;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;

public class interfaz3 extends JFrame implements ActionListener{ 
    public int x=0, id=1,bsel,w=0;
    public String nom,valorc1,valorc2,valorc3,idf,idc,bsels;
    JButton jb1, jb2, jb3,jb3s, bcambiar, bc = new JButton("Confirmar"),bcv = new JButton("Confirmar compra"), direccion = new JButton("Menú principal");
    JFrame fa = new JFrame(), ticket = new JFrame(), cambios = new JFrame(),cconfirm = new JFrame();
    JPanel pa = new JPanel(), principal = new JPanel();
    JScrollPane jsprincipal = new JScrollPane();
    BoxLayout bl1 = new BoxLayout(principal,BoxLayout.PAGE_AXIS);
    FlowLayout dis1 = new FlowLayout(FlowLayout.LEFT, 20,20);
    JPanel descripcion = new JPanel(dis1);
    JTextField jtvalorn = new JTextField(10);
    JTextField jtvalorc1 = new JTextField(10);
    JTextField jtvalorc2 = new JTextField(10);
    JTextField nuevojl = new JTextField(10);
    ArrayList<JTextField> jtc = new ArrayList<JTextField>();
    ArrayList<JLabel> rfc = new ArrayList<JLabel>();
    ArrayList<JLabel> nombre = new ArrayList<JLabel>();
    ArrayList<JLabel> correo = new ArrayList<JLabel>();

    ArrayList<JPanel> hijop = new ArrayList<JPanel>();
    public interfaz3(String sugeto, String cosa1,String cosa2,String T,String B1,String B2) {
        this.setTitle(T);
        this.setSize(400,400);
        this.setLocationRelativeTo(null);  
        principal.setLayout(bl1);      
        //Botones
        jb2 = new JButton(B2);jb2.setBackground(Color.WHITE);
        jb3 = new JButton("Hacer cambios");jb3.setBackground(Color.WHITE);
        direccion.setBackground(Color.WHITE);
        //Agregar Nombre
        fa.setSize(200,250);
        fa.setTitle("Agregar "+sugeto);
        fa.setResizable(false);
        pa.add(new JLabel("Agregar "+sugeto));
        pa.add(new JLabel("Nombre del "+sugeto));
        pa.add(jtvalorn);
        pa.add(new JLabel(cosa1+" del "+sugeto));
        pa.add(jtvalorc1);
        pa.add(new JLabel(cosa2+" del "+sugeto));
        pa.add(jtvalorc2);
        pa.add(bc);
        fa.add(pa);
        //Acciones de botones
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        direccion.addActionListener(this);
        bc.addActionListener(this);
        JPanel jpboton1 = new JPanel();
        jpboton1.add(jb2);
        jpboton1.add(jb3);
        JPanel jpboton2 = new JPanel();
        jpboton2.add(direccion);
        jpboton1.setBackground(Color.GRAY);
        jpboton2.setBackground(Color.GRAY);
        principal.add(jpboton1);
        principal.add(jpboton2);
        descripcion.setBackground(Color.lightGray);
        descripcion.add(new JLabel("ID              Proveedor                       RFC                        Correo electronico      "));
        principal.add(descripcion);
        try{
            Connection con1 = null;
            Conexion1 conect1 = new Conexion1();
            con1 = conect1.getConnection();
            String[] info = new String[3];
            String sql = "SELECT Proveedores.Proveedor AS prod, Proveedores.RFC AS prec, Proveedores.CorreoElectronico AS provee"+
            " FROM Proveedores";
            PreparedStatement pstm = con1.prepareStatement( sql );
            ResultSet res = pstm.executeQuery(); 
            System.out.println(res);
            while(res.next()){
                hijop.add(new JPanel());
                hijop.get(id-1).setLayout(dis1);
                info[0] = res.getString( "prod" );
                nombre.add(new JLabel(info[0]));
                info[1] = res.getString("prec");
                rfc.add(new JLabel(info[1]));                
                info[2] = res.getString( "provee");
                correo.add(new JLabel(info[2]));
                hijop.get(id-1).add(new JLabel(""+id));
                hijop.get(id-1).add(nombre.get(id-1));
                hijop.get(id-1).add(new JLabel(""));
                hijop.get(id-1).add(rfc.get(id-1));
                
                hijop.get(id-1).add(correo.get(id-1));
                principal.add(hijop.get(id-1));
                id++;
            }
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "No se pueden visualizar los datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        jsprincipal.setViewportView(principal);
        this.add(jsprincipal,BorderLayout.CENTER);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {//Acciones de los botones
        if (e.getSource() == jb2)
        {
            fa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fa.setVisible(true);
        }
        if(e.getSource() == jb3)
        {
            jtc.add(new JTextField(4));
            ArrayList<JPanel> aljpc = new ArrayList<JPanel>();
            JPanel jpc = new JPanel();
            jb3s = new JButton("Buscar");
            jb3s.addActionListener(this);
            BoxLayout disc = new BoxLayout(jpc,BoxLayout.PAGE_AXIS);
            jpc.setLayout(disc);
            cambios.setSize(200,250);
            cambios.setResizable(false);
            aljpc.add(new JPanel());
            aljpc.get(0).add(new JLabel("Ingrese el id del Proveedor: "));
            aljpc.get(0).add(jtc.get(w));
            aljpc.add(new JPanel());
            aljpc.get(1).add(jb3s);
            jtc.get(w).setText("0");
            jpc.add(aljpc.get(0));jpc.add(aljpc.get(1));
            cambios.add(jpc);
            cambios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            cambios.setVisible(true);
        }
        if(e.getSource() == jb3s)
        {
            System.out.println(idc);
            cambios.dispose();  
            bsel = opciones();
            switch(bsel)
            {
                case 0:
                    bsels = "Proveedor";
                    break;
                case 1:
                    bsels = "RFC";
                    break;
                case 2:
                    bsels = "CorreoElectronico";
                    break;
                default:
                
            }
            cconfirm.setSize(200,250);
            cconfirm.setResizable(false);
            bcambiar = new JButton("Confirmar cambio");
            bcambiar.addActionListener(this);
            JPanel jpcs = new JPanel();
            jpcs.add(new JLabel());
            jpcs.add(new JLabel("Ingrese el cambio del "+bsels));
            jpcs.add(nuevojl);
            jpcs.add(bcambiar);
            cconfirm.add(jpcs);
            cconfirm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            cconfirm.setVisible(true);
        }
        if(e.getSource() == bcambiar)
        {
            String cambio,sql;
            idc = jtc.get(w).getText();
            w++;
            int k = Integer.parseInt(idc), i;
            k--;
            cconfirm.dispose();
            cambio = nuevojl.getText();
            switch(bsel)
            {
                case 0:
                    i =0;
                    bsels = "Proveedor";
                    nombre.get(k).setText(cambio);
                    break;
                case 1:
                    i = 1;
                    bsels = "RFC";
                    rfc.get(k).setText(cambio);
                    break;
                case 2:
                    i = 2;
                    bsels = "CorreoElectronico";
                    correo.get(k).setText(cambio);
                    break;
                default:
                    bsels = "CorreoElectronico";
                    i =3;
            }
            try{
                Connection con1 = null;
                Conexion1 conect = new Conexion1();
                con1 = conect.getConnection();
                switch(i)
                {
                    case 0:
                        sql = "UPDATE Proveedores SET Proveedor = ?"+
                        "WHERE Id="+idc;       
                        break;
                    case 1:
                        sql = "UPDATE Proveedores SET RFC = ?"+
                        "WHERE Id="+idc;       
                        break;
                    case 2:
                        sql = "UPDATE Proveedores SET CorreoElectronico = ?"+
                        "WHERE Id="+idc;       
                        break;
                    default:
                        sql = "UPDATE Proveedores SET CorreoElectronico = ?"+
                        "WHERE Id="+idc;       
                }
                PreparedStatement pst = con1.prepareStatement(sql);
                pst.setString(1, cambio);
                pst.executeUpdate();
            }catch (SQLException | HeadlessException exc)
            {
                System.out.println(exc.getMessage());
                JOptionPane.showMessageDialog(this, "Los datos no fueron cambiados correctamente", "Error", JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
        }
        if (e.getSource() == bc)
        {
            nom = jtvalorn.getText();
            valorc1 = jtvalorc1.getText();
            valorc2 = jtvalorc2.getText();
            hijop.add(new JPanel());
            hijop.get(id-1).setLayout(dis1);
            idf = String.valueOf(id);
            hijop.get(id-1).add(new JLabel(idf));
            nombre.add(new JLabel(nom));
            hijop.get(id-1).add(nombre.get(id-1));
            hijop.get(id-1).add(new JLabel(""));
            rfc.add(new JLabel(valorc1));
            hijop.get(id-1).add(rfc.get(id-1));
            correo.add(new JLabel(valorc2));
            hijop.get(id-1).add(correo.get(id-1));
            principal.add(hijop.get(id-1));
            //agregar datos a la base de datos
            try{
                Connection con1 = null;
                Conexion1 conect = new Conexion1();
                con1 = conect.getConnection();
                String sql = "INSERT INTO Proveedores (Proveedor,RFC,CorreoElectronico)"+
                "VALUES (?,?,?)";
                PreparedStatement pst = con1.prepareStatement(sql);
                pst.setString(1, nom);
                pst.setString(2, valorc1);
                pst.setString(3, valorc2);
                pst.executeUpdate();
            }catch (SQLException | HeadlessException exc)
            {
                JOptionPane.showMessageDialog(this, "Los datos no fueron guardados correctamente", "Error", JOptionPane.ERROR_MESSAGE);
            }
            //
            fa.dispose();
            this.dispose();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            id++;
        }
        if(e.getSource() == direccion)
        {
            new App();
            this.dispose();
        }
    }
    public int opciones(){
		String[] options={"Nombre","RFC","Correo electronico"};
		int v= JOptionPane.showOptionDialog(null, 
		"Ingrese qué quiere cambiar", 
		"Cambios",
		JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null,
		options,
		options[0]);
		return (v);
	}
    public static void main(String[]args){
        
    }
}