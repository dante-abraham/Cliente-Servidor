package reto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ClienteUDP {

	public static void main(String[] args) {
		System.out.println("cliente...");
		try {
			DatagramSocket socketUDP = new DatagramSocket(); // Se crea el datagrama de conexion UDP
			int PUERTO = 8888; // Definimos puerto
			InetAddress HOST = InetAddress.getByName("localhost"); // definimos IP
			
			BufferedReader flujo = new BufferedReader(new InputStreamReader(System.in)); // flujo de entrada de datos por consola
			
			String cad;
			cad = flujo.readLine(); // leemos datos desde consola
			
			byte msg[] = cad.getBytes();
			DatagramPacket req = new DatagramPacket(msg, msg.length, HOST, PUERTO); // creamos emnsaje
			socketUDP.send(req); // enviamos mensaje
			
			// recibimos mensaje
			byte buffer[] = new byte[1024];
			DatagramPacket res = new DatagramPacket(buffer, buffer.length);
			socketUDP.receive(res);
			System.out.print("La cadena \"" + cad + "\" tiene "+ (new String(res.getData())).trim() + " palabras");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
