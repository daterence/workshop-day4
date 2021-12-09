package com.terence.workshop.d4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader inputStreamReader= null;
        OutputStreamWriter outputStreamWriter= null;
        BufferedWriter bufferedWriter= null;
        BufferedReader bufferedReader= null;

        //wait for client to 'contact' the server socket
        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(12345);

        while(true){
            try {
                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while(true){
                    String msgFromClient = bufferedReader.readLine();
                    System.out.println("Client: " + msgFromClient);

                    if(msgFromClient.equalsIgnoreCase("get-cookie")){
                        bufferedWriter.write("cookie-text "+ Cookie.randomCookie());
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } else if(msgFromClient.equalsIgnoreCase("close")){
                        break;
                    }
//                  msg to check server received input from Client side
                    bufferedWriter.write("Msg received");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
