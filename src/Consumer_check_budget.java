import javax.swing.*;
import java.awt.*;

/**
 * Created by oyh on 2018/4/16.
 */
public class Consumer_check_budget extends JPanel implements Runnable {
    private provider_controller p=new provider_controller();
    String path =System.getProperty("user.dir");
    public Consumer_check_budget(String i){
        jTextField1 =new JTextField();
        setLayout(null);
        jLabel1=new JLabel();
        setSize(300,100);
        setBackground(new Color(255,255,255));
        if(p.check_budget(i)) {
            jTextField1.setText("The budget is higher than current cost");
            jTextField1.setEditable(false);
            jTextField1.setBorder(null);
            jTextField1.setBounds(0,0,300,100);
            jTextField1.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
            jTextField1.setForeground(Color.GREEN);
            this.add(jTextField1);
        }
        else{
            jTextField1.setText("The budget is lower than current cost!");
            jTextField1.setBorder(null);
            this.add(jTextField1);
            jTextField1.setEditable(false);
            jTextField1.setBounds(0,0,300,100);
            jTextField1.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
            jTextField1.setForeground(Color.RED);
        }
    }

    public void run() {
        while (true) {
            this.invalidate();
            this.validate();
            this.updateUI();
            this.repaint();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
        }
    }
    JTextField jTextField1;
    JLabel jLabel1;

}
