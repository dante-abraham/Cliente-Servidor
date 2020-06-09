package reto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorTCP {

	public static void main(String[] args) throws IOException {
		ServerSocket socketServidor = null;
		Socket socketCliente = null;
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			socketServidor = new ServerSocket(8888);
			System.out.println("Servidor escuchando en puerto 8888...");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		try {
			while(true) {
				socketCliente = socketServidor.accept();
				System.out.println("Nuevo usuario aceptado");
				in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
				
				String cad = in.readLine(); // leemos del cliente el mensaje
				
				String msg = "tijera";
				if(cad.equals("1")) {
					msg = "papel";
				}
				else if(cad.equals("2")) {
					msg = "piedra";
				}
				out.println(msg); // Enviamos al cliente mensaje
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		in.close();
		out.close();
		socketServidor.close();
		socketCliente.close();
	}
}
