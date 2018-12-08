/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.List;

/**
 *
 * @author oyh
 */
public class Manager_Acc_add extends javax.swing.JDialog {
    private provider_controller p;
    private File_controller ff=new File_controller();
    /**
     * Creates new form NewJDialog
     */
    public Manager_Acc_add(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        p=new provider_controller();
        Date today=new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String D=ft.format(today);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(600,400);
        jPanel1=new JPanel();
        setContentPane(jPanel1);

        jPanel1.setLayout(null);
        jPanel1.setBackground(new Color(255,255,255));

        textField1 = new JTextField(15);
        textField2 = new JTextField(15);
        textField3 = new JTextField(15);
        textField4 = new JTextField("",15);
        textField5 = new JTextField("",15);
        textField6 = new JTextField("",15);
        textField7 = new JTextField(40);

        button1 = new JButton();
        button2 = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        textField1.setText("Account No");
        textField1.setEditable(false);
        textField1.setBorder(null);

        textField2.setText("Account Name");
        textField2.setEditable(false);
        textField2.setBorder(null);

        textField3.setText("Account Email");
        textField3.setEditable(false);
        textField3.setBorder(null);

        textField7.setText("Please input account information!");
        textField7.setBorder(null);
        textField7.setEditable(false);
        textField7.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N

        button1.setText("Add");
        button2.setText("Back");

        jPanel1.add(textField1);
        jPanel1.add(textField2);
        jPanel1.add(textField3);
        jPanel1.add(textField4);
        jPanel1.add(textField5);
        jPanel1.add(textField6);
        jPanel1.add(textField7);
        jPanel1.add(button1);
        jPanel1.add(button2);

        textField1.setBounds(70,80,100,40);
        textField2.setBounds(70,160,100,40);
        textField3.setBounds(70,240,100,40);

        textField4.setBounds(200,80,200,40);
        textField5.setBounds(200,160,200,40);
        textField6.setBounds(200,240,200,40);

        textField7.setBounds(70,30,500,40);

        button1.setBounds(450,80,80,40);
        button2.setBounds(450,240,80,40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ac = textField4.getText();
                    String name = textField5.getText();
                    String email = textField6.getText();
                    if(check_info(ac,name,email)) {
                        int accNo = Integer.parseInt(ac);
                        household h1 = new household();
                        h1.household_set(D, accNo, name, email, 0.0, 0.0);
                        p.newHousehold(h1);
                        JOptionPane.showMessageDialog(null, "The account have been created", null, JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Please input right information", null, JOptionPane.WARNING_MESSAGE);
                    }
                }catch (IOException f){
                    System.out.println("error!");
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame m=new Manager_Acc();
                m.setVisible(true);
            }
        });

    }// </editor-fold>

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialog dialog = new Manager_Acc_add(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private JButton button1;
    private JButton button2;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JPanel jPanel1;
    private JTextField textField7;


    //判断输入的信息是否格式化

    private boolean check_info(String a,String b,String c){
        String pattern1 = "^\\d{1,}$";
        String pattern2 = "^[a-zA-Z0-9]{6,20}$";
        String pattern3 ="\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";

        boolean matches1 = Pattern.matches(pattern1, a);
        boolean matches2= Pattern.matches(pattern2, b);
        boolean matches3 = Pattern.matches(pattern3,c);
        List<String> ss=ff.getData_User();
        boolean matches4=ss.contains(a);

        if(matches1&&matches2&&matches3&&!matches4){
            return true;
        }else{
            return false;
        }
    }


    // End of variables declaration
}

