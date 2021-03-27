package com.example.parcial2021_1;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection extends Thread {
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

    private Socket socket;
    private BufferedWriter writer;
    private OnMessagesListener observer;

    public void setObserver(OnMessagesListener observer) {
        this.observer = observer;
    }

    public void run() {
        try {
            socket = new Socket("10.0.2.2", 5000);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            writer = new BufferedWriter(osw);


        } catch (UnknownHostException e) {

        } catch (IOException e) {

        }
    }

    public void mensaje(String msg) {
        Gson gson = new Gson();

        String action = msg;

        String Str = gson.toJson(action);
        new Thread(
                () -> {
                    try {
                        writer.write(Str + "\n");
                        writer.flush();
                    } catch (IOException e) {

                    }
                }
        ).start();
    }
}

