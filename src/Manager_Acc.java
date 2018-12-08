import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

/**
 * Created by oyh on 2018/4/17.
 */
public class Manager_Acc extends JFrame {
    String path=System.getProperty("user.dir");

    private provider_controller p;
    public Manager_Acc(){
        initComponents();
    }

    private void initComponents(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(800,600);
        setBackground(new Color(255,255,255));
        p=new provider_controller();
        jPanel1 =new JPanel();
        jTable1 =new JTable();
        jScrollPane1=new JScrollPane();
        jButton1=new JButton();
        jButton2=new JButton();
        jButton3=new JButton();
        jTextField1 =new JTextField();

        jPanel1.setBackground(new Color(255,255,255));
        jPanel1.setLayout(null);
        String[] c=new String[]{"Account Number","Account Name","Account E-mail"};
        int[] columnWidth=new int[]{50,50,50};
        jTable1.setModel(getTableModel_account());
        TableColumnModel columnModel = jTable1.getColumnModel();// 获取列模型
        int count = columnModel.getColumnCount();// 获取列数量

        for (int i = 0; i < count; i++) {// 遍历列
            TableColumn column = columnModel.getColumn(i);// 获取列对象
            column.setHeaderValue(c[i]);
            column.setPreferredWidth(columnWidth[i]);// 以数组元素设置列的宽度
        }

        jTextField1.setText("Please select an account to remove or add new account");
        jTextField1.setBounds(80,20,500,40);
        jTextField1.setBorder(null);
        jTextField1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jTextField1.setEditable(false);
        jPanel1.add(jTextField1);

        jScrollPane1.setViewportView(jTable1);
        jScrollPane1.setBackground(new Color(255,255,255));
        jScrollPane1.setBounds(80,70,500,400);

        setContentPane(jPanel1);
        jPanel1.add(jScrollPane1);
        jButton1.setText("Remove");
        jButton1.setBounds(650,70,80,30);
        jButton2.setText("Add");
        jButton2.setBounds(650,300,80,30);
        jButton3.setText("Back");
        jButton3.setBounds(650,500,80,30);
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);
        jPanel1.add(jButton3);

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (jTable1.isCellSelected(jTable1.getSelectedRow(), jTable1.getSelectedColumn())) {
                        Object s = jTable1.getValueAt(
                                jTable1.getSelectedRow(),
                                0);
                        String ss = (String) s;
                        try {
                            p.closeHousehold(ss);
                            JOptionPane.showMessageDialog(null, ss+" have been removed", null, JOptionPane.INFORMATION_MESSAGE);
                            jTable1.updateUI();
                        }catch (IOException er) {
                            System.out.println("This occur a error");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Please select a account", null, JOptionPane.INFORMATION_MESSAGE);
                    }
                }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JDialog jDialog1=new Manager_Acc_add(new Frame(),true);
                jDialog1.setVisible(true);

            }
        });

        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame m=new Manager();
                m.setVisible(true);
            }
        });

    }

    private List<String> getData_account() {
        File f = new File(path + "/household/");
        File[] x = f.listFiles();
        List<String> s = new ArrayList<String>();
        for (File a : x) {
            if (a.isDirectory()) {
                FileReader fr;
                File file = new File(a + "/today.txt");
                int b;
                StringBuffer sb = new StringBuffer();

                try {
                    fr = new FileReader(file);
                    while ((b = fr.read()) != -1) {
                        if (b != '\r') {
                            sb.append((char) b);
                        }
                        if (b == '\n') {
                            s.add(sb.toString());
                            sb = new StringBuffer();
                        }
                    }
                    fr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }  return s;
    }

    private AbstractTableModel getTableModel_account() {
        return new AbstractTableModel() {
            public int getColumnCount() {
                return 3;
            }
            public int getRowCount() {
                return getData_account().size();
            }
            public Object getValueAt(int row, int col) {
                switch (col) {
                    case (0): {
                        String a=getData_account().get(row).split(",")[1];
                        return a;
                    }
                    case (1): {
                        String a=getData_account().get(row).split(",")[2];
                        return a;
                    }
                    case (2):{
                        String a=getData_account().get(row).split(",")[3];
                        return a;
                    }
                    default:
                        return "false";
                }
            }

        };
    }

    private JPanel jPanel1;
    private JTable jTable1;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JScrollPane jScrollPane1;
    private JTextField jTextField1;
}
