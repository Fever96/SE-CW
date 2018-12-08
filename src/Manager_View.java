import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by oyh on 2018/4/17.
 */
public class Manager_View extends JFrame{

    public Manager_View(){
        initComponents();
    }

    public void initComponents(){
        setSize(800,600);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        jPanel1=new Manager_View_Today();
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
                if(item=="Today"){
                    jPanel1.removeAll();
                    jPanel1.setVisible(false);
                    jPanel1.add(new Manager_View_Today());
                    jPanel1.setVisible(true);
                    jPanel1.updateUI();
                }
                else if(item=="Day"){
                    jPanel1.removeAll();
                    jPanel1.setVisible(false);
                    jPanel1.add(new Manager_View_Day());
                    jPanel1.setVisible(true);
                    jPanel1.updateUI();
                }else if(item=="Month"){
                    jPanel1.removeAll();
                    jPanel1.setVisible(false);
                    jPanel1.add(new Manager_View_Mouth());
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
                JFrame f1=new Manager();
                f1.setVisible(true);
            }
        });
    }

    public static void main(String[] args){
        JFrame f1=new Manager_View();
        f1.setVisible(true);
    }

    private JPanel jPanel1;
    private JPanel jPanel2;
    private JComboBox<String> jComboBox1;
    private JTable jTable1;
    private JButton jButton1;


}
