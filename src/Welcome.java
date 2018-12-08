
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author oyh
 */
public class Welcome extends javax.swing.JFrame {
    String path=System.getProperty("user.dir");
    public provider p;
    /**
     * Creates new form Welcome
     */
    public Welcome() {
        initComponents();
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(255,255,255));
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jLabel1 =new JLabel();
        jLabel2 =new JLabel();
        jButton3 =new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Smart Enengy Manage System!");

        setSize(800,600);
        setResizable(false);
        jTextField1.setText("Welcome to Smart Enengy Manage System!");
        jTextField1.setFont(new java.awt.Font("Lucida Grande", 0, 20)); // NOI18N
        jTextField1.setEditable(false);
        jTextField1.setBorder(null);

        jTextField2.setText("Please select your identity!");
        jTextField2.setEditable(false);
        jTextField2.setBorder(null);
        jTextField2.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N


        jButton1.setText("Manager");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JDialog Ma_pa=new Manager_Password(new JFrame(),true);
                Ma_pa.setVisible(true);
            }
        });

        jButton2.setText("Consumer");
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JDialog f1=new Consumer(new Welcome(),true);
                f1.setVisible(true);
            }
        });

        jButton3.setText("Exit");
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        setContentPane(jPanel1);
        jPanel1.setLayout(null);
        jTextField1.setBounds(200,20,500,50);
        jPanel1.add(jTextField1);
        jTextField2.setBounds(200,300,500,40);
        jPanel1.add(jTextField2);
        jButton1.setBounds(200,400,100,40);
        jPanel1.add(jButton1);
        jButton2.setBounds(500,400,100,40);
        jPanel1.add(jButton2);
        ImageIcon img = new ImageIcon(path+"/image/manager.png");// 创建图片对象
        img.setImage(img.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
        jLabel1.setIcon(img);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(100,350,100,100);
        ImageIcon img2=new ImageIcon(path+"/image/consumer.png");
        img2.setImage(img2.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
        jLabel2.setIcon(img2);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(400,350,100,100);
        jPanel1.add(jButton3);
        jButton3.setBounds(650,500,70,40);

    }// </editor-fold>

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify
    private JButton jButton1;
    private JButton jButton2;
    private JPanel jPanel1;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JButton jButton3;
    // End of variables declaration
}
