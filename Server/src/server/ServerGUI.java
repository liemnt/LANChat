/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author liemn
 */
public class ServerGUI extends javax.swing.JFrame {

    private ServerSocket server;
    // Danh sách người đang sử dụng
    public Hashtable<String, ThreadServer> listUser;
    
    /**
     * Creates new form ServerGUI
     */
    public ServerGUI() {
        initComponents();
        setVisible(true);
         user.append("Máy chủ đã được mở\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        user = new javax.swing.JTextArea();
        close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LAN Chat App");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Trạng thái Server: ");

        user.setColumns(20);
        user.setRows(5);
        jScrollPane1.setViewportView(user);

        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.closeServer();
    }//GEN-LAST:event_formWindowClosing

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
this.closeServer();        // TODO add your handling code here:
    }//GEN-LAST:event_closeActionPerformed
private void closeServer(){
try {
            server.close();
            System.exit(0);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
new ServerGUI().go();
        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ServerGUI window = new ServerGUI();
//                window.setVisible(true);
//                window.go();
//            }
//        });
        
//        Khoi tao mot doi tuong server;
        
    }
    
    public void addMessage(String msg){
    user.append(msg);
    }
    
     public void sendAll(String from, String msg){
        Enumeration e = listUser.keys();
        String name=null;
        while(e. hasMoreElements()){
            name=(String) e.nextElement();
            if(name.compareTo(from)!=0){
                listUser.get(name).sendMSG("3",msg);
                
                
            }
        }
    }
     
     public void sendAllUpdate(String from){
        Enumeration e = listUser.keys();
        String name=null;
        while(e. hasMoreElements()){
            name=(String) e.nextElement();
            //System.out.println(name);
            if(name.compareTo(from)!=0) listUser.get(name).sendMSG("4",getAllName());
        }
    }
     
     public String getAllName(){
        Enumeration e = listUser.keys();
        String name="";
        while(e. hasMoreElements()){
            name+=(String) e.nextElement()+"\n";
        }
        return name;
    }
     
     private void go(){
         System.out.println(this.user.toString());
//         this.user.setText("Ahihi");
        try {
            listUser = new Hashtable<String, ThreadServer>();
            // Mở một ServerSocket ở port 2207
            server = new ServerSocket(2207);            
            user.append("Máy chủ bắt đầu phục vụ\n");
            while(true){
                Socket client = server.accept();
                new ThreadServer(this,client);                
            }
        } catch (IOException e) {
            user.append("Không thể khởi động máy chủ\n");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea user;
    // End of variables declaration//GEN-END:variables
}