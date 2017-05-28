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
    private DataOutputStream outServer;
    private DataInputStream inServer;
    private DataOutputStream outClient;
    private DataInputStream inClient;
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
    private ControllerInterface controller1, controller2;

    //(LANManger.getInstance()).init(...);
    public final static LANManager getInstance() {
        if (instance == null) {
            instance = new LANManager();
        }
        return instance;
    }

    public void init(boolean isHost, String ipAddress, NES nes) throws IOException {
        this.isHost = isHost;
        this.ipAdress = ipAddress;
        this.nes = nes;
        initHost();
        initClient();
        initStream();
        //clientOrHost = new MultiplayerDialog();
    }

    private void initHost() {
        //if(clientOrHost.getClientOrHost)
        try {
            serverSocket = new ServerSocket(9999);

            while (true) {
                clientServerSocket = serverSocket.accept();

                /* getLatchByte(getcontroller1());
                getLatchByte(getcontroller1());*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initClient() {
        try {
            clientSocket = new Socket("localhost", 9999);
            controller1 = new NetworkController();
            controller2 = new NetworkController();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initStream() throws IOException {

        inServer = new DataInputStream(clientServerSocket.getInputStream());
        outServer = new DataOutputStream(clientServerSocket.getOutputStream());
        this.inClient = new DataInputStream(clientSocket.getInputStream());
        this.outClient = new DataOutputStream(clientSocket.getOutputStream());

    }

    public void controllerSendReceive() throws IOException {

        if (!isHost) {
            controllerbyte = ((ControllerImpl) controller2).getLatchByte();
            outClient.write(controllerbyte);
            outClient.flush();
            NetworkController controllerNet = new NetworkController();
            controllerNet.setNetByte(inClient.readInt());
            controller2 = controllerNet;
        } else {
            controllerbyte = ((ControllerImpl) controller1).getLatchByte();
            outServer.write(controllerbyte);
            outServer.flush();
            NetworkController controllerNet = new NetworkController();
            controllerNet.setNetByte(inServer.readInt());
            controller1 = controllerNet;
        }
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
            this.outServer.writeUTF("ready");
            System.out.println("Ready out!");
            new Thread(getInstance()).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (clientServerSocket != null) {
            try {
                clientServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (clientSocket != null) {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetConnection() throws IOException {
        closeConnection();
        if (clientServerSocket != null) {
            initHost();
        }
        if (clientSocket != null) {
            initClient();
        }
        initStream();
    }

    public void setPathROM(String ROMpath) {
        this.ROMpath = ROMpath;
    }

}
