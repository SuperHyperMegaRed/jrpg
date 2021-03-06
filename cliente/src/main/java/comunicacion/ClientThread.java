package comunicacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.codehaus.jackson.map.ObjectMapper;

import partida.Introduccion;
import partida.Partida;


public class ClientThread implements Runnable {
    private Socket socket;
    private Scanner sc;
    private PrintWriter out;
    private String mapaSeleccionado;



    public ClientThread(Socket socket, String mapaSeleccionado) {
        this.socket = socket;
        this.mapaSeleccionado=mapaSeleccionado;
    }

    @Override
    public void run() {

        JFrame jf = new JFrame("The Bug");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(650,400);
        jf.add(new Introduccion());
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, "Error en la intro");
		}

        jf.dispose();
        
    	Partida game = new Partida("The bug", 640, 480, socket);
		game.start();
    	
    }


    private void recibirDatos() {
        if (this.sc.hasNext()) {
            String mensajeEntrante = this.sc.nextLine();
        }
    }

    public void enviarDatos(String mensajeSaliente) {
        this.out.println(mensajeSaliente);
        this.out.flush();
    }

    public void desconectar() throws Exception {
        this.out.println(" se ha retirado de la sala");
        this.out.flush();
        this.socket.close();
        System.exit(0);
    }
}
