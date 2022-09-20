/////////////////////////////////////////////////////////////////
////////////// program for Client Side //////////////////////////
///////////////// Socket Programming ///////////////////////////

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

////////////// helpdesk2 class ////////////////////////////////////
public class Client extends Frame implements ActionListener {

    ////////////////// charactericts of helpdesk2 class ////////////////
    Label l, ser1, ser2, cl1;
    TextField t1;
    Button b1;
    Socket s;
    ServerSocket ss;
    String str1, str2;

    /////////////////// helpdesk Class Constructor ///////////////////
    public Client() throws Exception {
        setBackground(new Color(236, 229, 221));
        setTitle("Client");
        setSize(400, 300);
        setLayout(null);
        setVisible(true);

        //////////// function for closing window ////////////////////////////////
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent w) {
                System.exit(0);
            }
        });

        ////////////////// Label, textfield and buttons /////////////////
        Font f1 = new Font("Georgia", Font.BOLD, 20);
        Font f2 = new Font("Georgia", Font.BOLD, 13);

        l = new Label("Chat with Server");
        l.setBounds(8, 30, 400, 50);
        l.setFont(f1);
        l.setBackground(new Color(18, 140, 126));
        l.setForeground(Color.white);
        add(l);

        b1 = new Button("Send");
        b1.setBounds(330, 250, 50, 30);
        b1.setBackground(new Color(18, 140, 126));
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        add(b1);

        ser1 = new Label();
        ser1.setBounds(18, 100, 250, 23);
        ser1.setFont(f2);
        ser1.setBackground(Color.white);

        cl1 = new Label();
        cl1.setBounds(100, 150, 290, 23);
        cl1.setFont(f2);
        cl1.setBackground(new Color(220, 248, 198));

        ser2 = new Label();
        ser2.setBounds(18, 200, 250, 23);
        ser2.setFont(f2);
        ser2.setBackground(Color.white);

        t1 = new TextField();
        t1.setBounds(20, 250, 300, 20);
        add(t1);

        s = new Socket("localhost", 2101);
        while (true) {
            try{
            DataInputStream dis = new DataInputStream(s.getInputStream());
            str1 = dis.readUTF();
            ser1.setText(str1);
            add(ser1);
            str2 = dis.readUTF();
            ser2.setText(str2);
            add(ser2);
            }catch(Exception ee){
                System.exit(0);
            }
        }
    }

    ///////////// method which implements actionListener//////////
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() == b1) && (t1.getText() != "")) {
            cl1.setText(t1.getText());
            add(cl1);
            try {
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF(t1.getText());
            } catch (Exception e1) {
                try {
                    //Thread.sleep(3000);
                    System.exit(0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            t1.setText("");
        }
    }

    ///////////// Entry gate main function ////////////////////
    public static void main(String[] args) throws Exception {
        Client obj = new Client();
    }
} 