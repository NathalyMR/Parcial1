package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;


public class Connection extends Thread{
	
	private static Connection instancia;

	public static Connection getInstance() {
        if (instancia == null) {
            instancia = new Connection();
            instancia.start();
        }
        return instancia;
    }
	
	private Connection() {
    }
	
	String recordatorio;
	private OnMessagesListener OML;
	
	 public void setObserver(OnMessagesListener observer){
	        this.OML = observer;
	    }
	 
	public void run () {
		
		try {
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(5000);
			System.out.println("Esperando cliente...");
			Socket socket = server.accept();
			System.out.println("Cliente está conectado");

			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader breader = new BufferedReader(isr);

			while (true) {
				
				System.out.println("Esperando mensaje...");
				String mensajeRecibido = breader.readLine(); 
				System.out.println(mensajeRecibido);

				Gson gson = new Gson();

				
				recordatorio = gson.fromJson(mensajeRecibido, String.class);
				OML.OnMessage(recordatorio);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}
