/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author oyh
 */
public class Manager_Password extends javax.swing.JDialog {
    private provider_controller p;
    /**
     * Creates new form NewJDialog2
     */
    public Manager_Password(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        p=new provider_controller();
        setBounds(100,100,500,250);
        setSize(500,250);
        setResizable(false);
        setTitle("Manager Password ");
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JPasswordField(15);
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTextField1.setText("Please input the password!");
        jTextField1.setEditable(false);
        jTextField1.setSelectionStart(26);
        jTextField1.setBorder(null);
        jTextField1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jTextField2.setEchoChar('*');
        String s=p.get_password();
        jButton1.setText("Enter");
        jButton2.setText("Back");

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ss=jTextField2.getText();
                if (ss.equals(s)){
                    setVisible(false);
                    JFrame f1=new Manager();
                    f1.setVisible(true);
                }
                else if(ss.equals("")){
                    JOptionPane.showMessageDialog(null, "Password is null, Please input again!", "alert", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Password Error!", "alert", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame f1=new Welcome();
                f1.setVisible(true);
            }
        });

        setContentPane(jPanel2);
        jPanel2.setLayout(null);
        jPanel2.add(jTextField1);
        jTextField1.setBounds(70,30,300,50);
        jPanel2.add(jTextField2);
        jTextField2.setBounds(70,130,100,30);
        jPanel2.add(jButton1);
        jButton1.setBounds(300,130,70,30);
        jPanel2.add(jButton2);
        jButton2.setBounds(300,170,70,30);

    }// </editor-fold>


    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPasswordField jTextField2;
    // End of variables declaration
}