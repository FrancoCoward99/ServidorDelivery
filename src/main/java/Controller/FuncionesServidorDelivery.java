/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Franco Coward
 */
public class FuncionesServidorDelivery {
       private static final String HOST = ""; //La direccion ip en donde se encuentra el servidor
    private static final int PUERTO = 5000;//Este es el puerto de donde se conecta el cliente 

    private ServerSocket servidor; //Aca se genera las entradas y salidas del servidor
    private Socket cliente;//aqui se genera las entradas y las salidas del clientes

    private DataOutputStream salida;//Paquetes que salen del servidor
    private DataInputStream entrada;//paquetes que entran al servidor

    private String mensajeRecibido = "";

    public void inciarServidor() {
        Scanner lectura = new Scanner(System.in);

        try {
            servidor = new ServerSocket(PUERTO);
            cliente = new Socket();
            System.out.println("Servidor inciado. esperando conexiones externas....");

            cliente = servidor.accept();
            System.out.println("Se conecto un cliente");

            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

            String mensajeEnviado = "";

            while (!mensajeEnviado.equals("SALIR")) {
                
                //Me voy a comunicar con el cliente
                mensajeRecibido = entrada.readUTF();
                System.out.println("Mensaje del cliente: " + mensajeRecibido);

                //Puedo generar mesajes automatizados
                if (mensajeRecibido.equalsIgnoreCase("Hola")) {
                    mensajeEnviado = "Hola cliente, en que le puedo ayudar?";
                    salida.writeUTF(mensajeEnviado);
                } else {
                    System.out.println("Digite una respuesta para el cliente....");
                    mensajeEnviado = lectura.nextLine();
                    salida.writeUTF(mensajeEnviado);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Se presentó un error: " + e.getMessage());
        }
    }
}
