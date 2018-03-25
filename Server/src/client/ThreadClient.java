/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package client;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author liemn
 */
public class ThreadClient extends Thread {
    private boolean run;
    private DataInputStream dis;
    private ClientGUI client;
    public ThreadClient(ClientGUI client, DataInputStream dis){
        run=true;
        this.client=client;
        this.dis=dis;
        
        this.start();
    }
    
    
    public void run(){
        String msg1,msg2;
        while(run){
            try {
                msg1=dis.readUTF();
                msg2=dis.readUTF();
                client.getMSG(msg1,msg2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stopThread(){
        this.run=false;
    }
}
