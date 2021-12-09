package com.terence.workshop.d4;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;

        try{
            socket = new Socket("localhost", 12345);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            Scanner scanner = new Scanner(System.in);
            while (true){

                String msgToSend = scanner.nextLine();
                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();

//                if(bufferedReader.readLine().contains("Msg received")){
//                    System.out.println("Server: " + bufferedReader.readLine());
//                } else if(bufferedReader.readLine().contains("cookie-text")){
//                    String msgOut = bufferedReader.readLine().replace("cookie-text", "");
//                    System.out.println("Server: " + msgOut.trim());
//                }
                System.out.println("Server: " + bufferedReader.readLine());

                if(msgToSend.equalsIgnoreCase("close"))
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(socket!=null)
                    socket.close();
                if(inputStreamReader!=null)
                    inputStreamReader.close();
                if(outputStreamWriter!=null)
                    outputStreamWriter.close();
                if(bufferedReader!=null)
                    bufferedReader.close();
                if(bufferedWriter!=null)
                    bufferedWriter.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}