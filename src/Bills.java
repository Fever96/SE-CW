import javax.swing.*;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 * Created by oyh on 2018/5/16.
 */
public class Bills implements Send_Bills{
    String path=System.getProperty("user.dir");
    provider_controller p=new provider_controller();
    private File_controller ff=new File_controller();
    public Bills(){

    }
    public void email(String i){
        Date today=new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");
        String D=ft.format(today);
        String a1="";
        String a2="";
        String a3="";
        File f1 = new File(path+"/household/"+i+"/today.txt");
        try{
            BufferedReader br1=new BufferedReader(new FileReader(f1));
            String b1=br1.readLine();
            String b2=b1.trim().split(",")[0].split(" ")[0].split("-")[0];
            String b3=b1.trim().split(",")[0].split(" ")[0].split("-")[1];
            String[] b4=b1.trim().split(",");
            a1=b2+"-"+b3;
            File f3 = new File(path+"/household/"+i+"/mouth.txt");
            a3=ff.readLastNLine(f3);
            String c1=a3.trim().split(",")[0];
            String[] c2=a3.trim().split(",");
            if(c1==a1){
                Double d1 = Double.parseDouble(c2[4]);
                Double d2 = Double.parseDouble(c2[5]);
                Double d3 = Double.parseDouble(c2[6]);
                Double d4 = Double.parseDouble(c2[7]);
                Double d5 = Double.parseDouble(b4[4]);
                Double d6 = Double.parseDouble(b4[5]);
                Double d7 = Double.parseDouble(b4[6]);
                Double d8 = Double.parseDouble(b4[7]);
                BigDecimal g1   =   new   BigDecimal(d1+d5);
                double e1 = g1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                BigDecimal g2   =   new   BigDecimal(d2+d6);
                double e2 = g2.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                BigDecimal g3   =   new   BigDecimal(d3+d7);
                double e3 = g3.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                BigDecimal g4   =   new   BigDecimal(d4+d8);
                double e4 = g4.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                String h1 = String.valueOf(e1);
                String h2 = String.valueOf(e2);
                String h3 = String.valueOf(e3);
                String h4 = String.valueOf(e4);
                a2=h1+","+h2+","+h3+","+h4;
            }
            else{
                a2=b4[4]+","+b4[5]+","+b4[6]+","+b4[7];
            }
        }catch (IOException r2){

        }

        System.out.println(a2);
        File f2 = new File(path + "/bills/"+i+"_"+D+".txt");
        try {
            if (!f2.exists()) {
                f2.createNewFile();
                BufferedWriter bw1=new BufferedWriter(new FileWriter(f2));
                bw1.write(a2);
                bw1.close();
            }
            else{
                f2.delete();
                BufferedWriter bw2=new BufferedWriter(new FileWriter(f2));
                bw2.write(a2);
                bw2.close();
            }
        }catch (IOException r){

        }
        JOptionPane.showMessageDialog(null,"Bill sends to consumer "+i,"Bills",INFORMATION_MESSAGE);
    }

}
