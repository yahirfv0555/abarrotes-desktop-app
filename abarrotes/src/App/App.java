package App;

import interfaces.*;
import javax.swing.*;

public class App extends JFrame{
    public App() {
        this.setTitle("Sistema LBJJAY para administrar el proceso de ventas de la tienda de abarrotes 'Don Luis'");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        JPanel Principal = new JPanel();
        Principal.add(new JLabel("Sistema LBJJAY para administrar el proceso de ventas de la tienda de abarrotes 'Don Luis'"));
        this.add(Principal);
        String[] opciones = {"Ventas","Clientes","Proveedores","Salir"};
        int v = JOptionPane.showOptionDialog(this, 
		"¿A donde quieres ir?", 
		"Menú",
		JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null,
		opciones,
		opciones[0]);
        switch(v)
        {
            case 0:
                new interfaz1("Producto","Precio","Proveedor","Ventas","Finalizar Compra","Agregar producto" );
                this.dispose();
                break;
            case 1:
                new interfaz2("Cliente","RFC","Correo electronico","Clientes","Salir","Agregar cliente" );
                this.dispose();
                break;
            case 2:
                new interfaz3("Proveedor","RFC","Correo electronico","Proveedores","Salir","Agregar proveedor");
                this.dispose();
                break;
            case 3:
                System.exit(0);
                break;
            default:
        } 
    }
    public static void main(String[]args)
    {
        new App();
    }   
}

