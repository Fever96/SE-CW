import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by oyh on 2018/4/17.
 */
public class Consumer_View extends JFrame{

    public Consumer_View(String i){
        initComponents(i);
    }

    public void initComponents(String i){
        setSize(800,600);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        jPanel1=new Consumer_View_Today(i);
        jPanel2=new JPanel();
        jButton1=new JButton();

        jPanel1.setBounds(0,0,600,600);
        jPanel2.setBounds(600,0,200,600);
        jPanel1.setBackground(new Color(255,255,255));
        jPanel2.setBackground(new Color(255,255,255));
        jPanel2.setLayout(null);
        add(jPanel1);
        add(jPanel2);
        String[] s=new String[]{"Today","Day","Month"};

        jComboBox1=new JComboBox<String>(s);
        jPanel2.add(jComboBox1);
        jComboBox1.setBounds(50,70,100,40);
        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item=(String) jComboBox1.getSelectedItem();
                if(item.equals("Today")){
                    jPanel1.removeAll();
                    jPanel1.setVisible(false);
                    jPanel1.add(new Consumer_View_Today(i));
                    jPanel1.setVisible(true);
                    jPanel1.updateUI();
                }
                else if(item.equals("Day")){
                    jPanel1.removeAll();
                    jPanel1.setVisible(false);
                    jPanel1.add(new Consumer_View_Day(i));
                    jPanel1.setVisible(true);
                    jPanel1.updateUI();
                }else if(item.equals("Month")){
                    jPanel1.removeAll();
                    jPanel1.setVisible(false);
                    jPanel1.add(new Consumer_View_Mouth(i));
                    jPanel1.setVisible(true);
                    jPanel1.updateUI();
                }
            }
        });

        jButton1.setText("Back");
        jButton1.setBounds(50,400,100,40);
        jPanel2.add(jButton1);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame f1=new Consumer_main(i);
                f1.setVisible(true);
            }
        });
    }

    private JPanel jPanel1;
    private JPanel jPanel2;
    private JComboBox<String> jComboBox1;
    private JButton jButton1;


}
