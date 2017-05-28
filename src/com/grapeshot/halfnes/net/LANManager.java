package com.grapeshot.halfnes.net;

import com.grapeshot.halfnes.NES;
import com.grapeshot.halfnes.ui.GUIImpl;
import com.grapeshot.halfnes.ui.ControllerInterface;
import com.grapeshot.halfnes.ui.ControllerImpl;
import com.grapeshot.halfnes.ui.MultiplayerDialog;
import com.grapeshot.halfnes.ui.NetworkController;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

//DÉCLARATION PACKAGES ET IMPORTS

/**
 *
 * @author
 */


public class LANManager implements Runnable {

    //Déclaration des variables à faire

    private static LANManager instance;
    private DataOutputStream out;
    private DataInputStream in ;
    private boolean isHost;
    private String ipAdress;
    private NES nes;

    private Socket clientServerSocket = null; //pour envoyer des donnees vers le serveur

    private Socket clientSocket = null;
    private ServerSocket serverSocket = null;
    private String ROMpath;
    private MultiplayerDialog clientOrHost;
    /*private BufferedInputStream = null;*/
    private int controllerbyte;
    private NetworkController controller1, controller2;

    //(LANManger.getInstance()).init(...);
    public final static LANManager getInstance() {
        if(instance == null){
            instance = new LANManager();
        }
        return instance;
    }

    public void init(boolean isHost, String ipAddress, NES nes) {
        this.isHost = isHost;
        this.ipAdress = ipAddress;
        this.nes = nes;
        //clientOrHost = new MultiplayerDialog();
    }

    private void initHost() {
        //if(clientOrHost.getClientOrHost)
        try {
            serverSocket = new ServerSocket(9999);

            while (true){
                clientSocket = serverSocket.accept();
    /*            in = new DataInputStream(clientSocket.getOutputStream());
                out = new DataOutputStream(clientSocket.getInputStream());*/

               /* getLatchByte(getcontroller1());
                getLatchByte(getcontroller1());*/
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    private void initClient() {
        try {
            clientSocket = new Socket("localhost", 9999);

            /*     in = new DataInputStream(clientSocket.getLatchByte(getcontroller2()));
                out = new DataOutputStream(clientSocket.getbyte(getcontroller2()));*/
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void initStream() throws IOException {
/*        this.out = new DataInputStream(clientSocket.getOutputStream());
        this.in = new DataOutputStream(clientSocket.getInputStream());;
        controller1 = new NetworkController();
        controller2 = new NetworkController();*/
    }

    public void controllerSendReceive() throws IOException {
        int buffer = 0;
/*        if(clientOrHost == isHost) {
            controllerbyte = in.read((ControllerImpl)controller1.getLatchByte());
            //clientSocket = out.controllerbyte(buffer);
            out.write(controllerbyte);
            out.flush();
            ((NetworkController)controllernum).setNetByte = in.clientSocket;
            controller2 = controllernum;
        }else{
            controllerbyte = in.read((ControllerImpl)controller2.getLatchByte());
            serverSocket = out.controllerbyte(buffer);
            ((NetworkController)controllernum).setNetByte = in.serverSocket;
            controller1 = controllernum;
        }*/
    }

    public void runEmulation(boolean state) {

    }

    @Override
    public void run() {
/*        (new Thread(new runEmulation))).startEventQueue();
        boolean ready = false;
        try {
            if (reading.equals("ready")) {
                System.out.println("Ready in!");
                ready = true;

            } else {
                System.out.println("Did not receive ready closing");
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }*/
    }

     /* Send "ready" state and launch a Thread for receive the same state in run() method
      */

    public void ready() {
        try {
            this.out.writeUTF("ready");
            System.out.println("Ready out!");
            new Thread(getInstance()).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
/*        if(connected!=null){
            try {
                this.connected.in.close();
                this.connected.out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    public void resetConnection() {
        try {
            this.in.close();
            this.out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.initHost();
    }

    public void setPathROM(String ROMpath) {
        this.ROMpath = ROMpath;
    }

}
