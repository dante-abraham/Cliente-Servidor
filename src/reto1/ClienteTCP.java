package reto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class ClienteTCP {

	public static void main(String[] args) throws IOException {
		
		Socket socketCliente = null;
		BufferedReader in = null;
		PrintWriter out = null; 
		
		try {
			socketCliente = new Socket("localhost", 8888);
			in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		BufferedReader flujo = new BufferedReader(new InputStreamReader(System.in));
		boolean sw = false;
        int opcion;
 
        while (!sw) { 
            System.out.println("1. Opcion 1");
            System.out.println("2. Opcion 2");
            System.out.println("3. Opcion 3");
            System.out.println("4. Salir");
 
            try {
                System.out.println("Escribe un número de opción");
                opcion = Integer.parseInt(flujo.readLine());
 
                if(opcion == 4) {
                	sw = true;
                }
                else if(opcion <= 3 && opcion > 0) {
                	out.println(opcion);
                	String cad = in.readLine();
        			System.out.println("Respuesta del servidor: " + cad);
                }
                else {
                	System.out.println("Opcion no valida");
                }
            } 
            catch (Exception e) {
                System.out.println("Debes insertar un número");
            }
        }
		
		in.close();
		out.close();
		flujo.close();
		socketCliente.close();
	}
}
