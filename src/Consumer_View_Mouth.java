/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oyh
 */
public class Consumer_View_Mouth extends javax.swing.JPanel {
    String path = System.getProperty("user.dir");
    private File_controller ff=new File_controller();

    /**
     * Creates new form NewJPanel2
     */
    public Consumer_View_Mouth(String i) {

        initComponents(i);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents(String i) {
        setSize(600, 600);

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(null);
        setBackground(new Color(255, 255, 255));


        jTable1.setModel(getTableModel(i));
        String[] c = new String[]{"Date", "Ele", "Ele cost", "Gas", "Gas cost"};
        int[] columnWidth = new int[]{100, 50, 50, 50, 50};

        TableColumnModel columnModel = jTable1.getColumnModel();// 获取列模型
        int count = columnModel.getColumnCount();// 获取列数量
        for (int a = 0; a < count; a++) {// 遍历列
            TableColumn column = columnModel.getColumn(a);// 获取列对象
            column.setHeaderValue(c[a]);
            column.setPreferredWidth(columnWidth[a]);// 以数组元素设置列的宽度
        }

        jScrollPane1.setViewportView(jTable1);

        jScrollPane1.setBounds(80, 60, 500, 400);
        add(jScrollPane1);

    }// </editor-fold>

    private AbstractTableModel getTableModel(String i) {
        return new AbstractTableModel() {
            public int getColumnCount() {
                return 5;
            }

            public int getRowCount() {
                return ff.getData(i,"mouth").size();
            }

            public Object getValueAt(int row, int col) {
                switch (col) {
                    case (0): {
                        return ff.getData(i,"mouth").get(row).split(",")[0];
                    }
                    case (1): {
                        return ff.getData(i,"mouth").get(row).split(",")[4];
                    }
                    case (2): {
                        return ff.getData(i,"mouth").get(row).split(",")[5];
                    }
                    case (3): {
                        return ff.getData(i,"mouth").get(row).split(",")[6];
                    }
                    case (4): {
                        return ff.getData(i,"mouth").get(row).split(",")[7].trim();
                    }
                    default:
                        return "false";
                }
            }

        };
    }


    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration
}
