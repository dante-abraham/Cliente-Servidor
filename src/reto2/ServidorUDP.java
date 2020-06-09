package reto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ServidorUDP {

	public static void main(String[] args) {
		System.out.println("Servidor en puerto 8888...");
		try {
			DatagramSocket socketUDP = new DatagramSocket(8888);
			byte buffer[] = new byte[1024];
			
			while(true) {
				DatagramPacket req = new DatagramPacket(buffer, 1024);
				socketUDP.receive(req); // recibimos la peticion del cliente
				System.out.println("nuevo cliente conectado...");
				
				String cad = new String(req.getData());
				int cant = contarPalabras(cad.trim());
				String msg = cant + "";
				
				cant = 0;
				DatagramPacket res = new DatagramPacket(msg.getBytes(), (msg.getBytes()).length, req.getAddress(), req.getPort());
				socketUDP.send(res);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int contarPalabras(String cad) {
		if(cad.length() == 0) return 0;
		String v[] = cad.split(" ");
		return v.length;
	}
}
