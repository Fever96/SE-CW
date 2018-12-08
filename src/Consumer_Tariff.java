/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author oyh
 */
public class Consumer_Tariff extends JFrame {
    String path=System.getProperty("user.dir");
    /**
     * Creates new form NewJPanel1
     */
    public Consumer_Tariff(String i) throws IOException {
        initComponents(i);
    }

    private void initComponents(String i) throws IOException{
        setSize(800,600);
        setResizable(false);

        jPanel =new JPanel();
        setContentPane(jPanel);
        jPanel.setBackground(new Color(255,255,255));
        File f=new File(path+"/tariff.txt");
        BufferedReader bw1=new BufferedReader(new FileReader(f));
        String aa=bw1.readLine();
        String[] s=new String[2];
        if(aa!=null){
            String a=aa.split(",")[0];
            String b=aa.split(",")[1].trim();
            s[0]=a;
            s[1]=b;
            aa=bw1.readLine();
        }
        jPanel.setLayout(null);

        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new JTextField();
        jButton1 =new JButton();

        jPanel.add(jTextField1);
        jPanel.add(jTextField2);
        jPanel.add(jTextField3);
        jPanel.add(jTextField4);
        jPanel.add(jTextField5);
        jPanel.add(jButton1);


        setBackground(new java.awt.Color(255, 255, 255));

        jTextField5.setText("These are all tariffs!");
        jTextField5.setFont(new java.awt.Font("Lucida Grande", 0, 25));
        jTextField5.setEditable(false);
        jTextField5.setBorder(null);

        jTextField1.setText("Eletric tariff");
        jTextField1.setFont(new java.awt.Font("Lucida Grande", 0, 20));
        jTextField1.setEditable(false);
        jTextField1.setBorder(null);

        jTextField2.setText("Gas tariff");
        jTextField2.setFont(new java.awt.Font("Lucida Grande", 0, 20));
        jTextField2.setEditable(false);
        jTextField2.setBorder(null);

        jTextField3.setText(s[0]);
        jTextField3.setFont(new java.awt.Font("Lucida Grande", 0, 20));
        jTextField3.setEditable(false);

        jTextField4.setText(s[1]);
        jTextField4.setFont(new java.awt.Font("Lucida Grande", 0, 20));
        jTextField4.setEditable(false);

        jTextField5.setBounds(150,50,500,50);

        jTextField1.setBounds(150,150,120,50);
        jTextField2.setBounds(150,350,120,50);

        jTextField3.setBounds(400,150,100,50);
        jTextField4.setBounds(400,350,100,50);

        jButton1.setText("Back");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame f1=new Consumer_main(i);
                f1.setVisible(true);
            }
        });
        jButton1.setBounds(550,450,100,40);
    }// </editor-fold>

    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private JTextField jTextField5;
    private JPanel jPanel;
    private JButton jButton1;

    // End of variables declaration
}
