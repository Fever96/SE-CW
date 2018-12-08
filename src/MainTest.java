import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oyh on 2018/6/5.
 */
public class MainTest {

    @Test
    public void mainTest() throws IOException{
    }

    @Test
    public void login_test(){
        String path=System.getProperty("user.dir");
        File f=new File(path+"/password.txt");
        if(f.exists()){
            try{
                BufferedReader br1=new BufferedReader(new FileReader(f));
                String s=br1.readLine().trim();
                Assert.assertEquals("Login successful!","123456",s);
                br1.close();
            }catch (IOException r){
                System.out.println("password file is not exist!");
            }
        }
    }

    @Test
    public void init_test(){
        String path=System.getProperty("user.dir");
        File_controller fc=new File_controller();
        provider_controller p=new provider_controller();
        p.init();
        List<String> s=fc.getData_User();
        for (String i:s) {
            ArrayList<String> a1=new ArrayList<>();
            ArrayList<String> a2=new ArrayList<>();
            File f = new File(path + "/household/"+i+"/today.txt");
            if(f.exists()){
                try {
                    BufferedReader br1 = new BufferedReader(new FileReader(f));
                    String s1=br1.readLine().trim();
                    a1.add(s1);
                    br1.close();
                    Thread.sleep(25000);
                    BufferedReader br2 =new BufferedReader(new FileReader(f));
                    String s2=br2.readLine().trim();
                    a2.add(s2);
                    br2.close();
                }catch (IOException|InterruptedException e){
                    System.out.println("Today's file is not exist");
                }
            }
            boolean m;
            if(a1.get(0).equals(a2.get(0))){
                m=true;
            }else
                m=false;
            Assert.assertEquals("Init successful!",false,m);
        }
    }

    @org.junit.Test
    public void init_tariff() throws Exception {
        String path = System.getProperty("user.dir");
        provider_controller p1 = new provider_controller();
        p1.init_tariff();
        File f = new File(path + "/tariff.txt");
        BufferedReader br1 = new BufferedReader(new FileReader(f));
        String s=br1.readLine();
        String a = s.trim().split(",")[0];
        String b = s.trim().split(",")[1];
        String[] c = p1.view_tariff();
        Assert.assertEquals("Eletric tariff initial success!", a, c[0]);
        Assert.assertEquals("Gas tariff initial success!", b, c[1]);
    }

    @org.junit.Test
    public void set_tariff_test() throws IOException {
        provider_controller p = new provider_controller();
        Double a = 4.0;
        Double b = 5.0;
        p.set_tariff(a, b);
        String[] c = p.view_tariff();
        Assert.assertEquals("Eletric tariff initial success!", String.valueOf(a), c[0]);
        Assert.assertEquals("Gas tariff initial success!", String.valueOf(b), c[1]);
    }

    @Test
    public void newhousehold_closehousehold_test() throws IOException {
        String path = System.getProperty("user.dir");
        provider_controller p = new provider_controller();
        household h = new household();
        Date today = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String D = ft.format(today);
        h.household_set(D, 8, "A", "ou@gmail.com", 0.0, 0.0);
        p.newHousehold(h);
        File f = new File(path + "/household/8");
        File f1=new File(path+"/household/8/today.txt");
        File f2=new File(path+"/household/8/mouth.txt");
        File f3=new File(path+"/household/8/day.txt");
        File f4=new File(path+"/household/8/bugdet.txt");
        Assert.assertEquals("Create new household successful!", true,
                f.exists() && f.isDirectory() && f1.exists()&&f2.exists()&&f3.exists()&&f4.exists());

        //File f6 = new File(path + "/household/8");
        //p.closeHousehold("8");
        //Assert.assertEquals("Close household successful!", false, f6.exists());
    }

    @Test
    public void closehousehold_test() throws IOException{
        String path = System.getProperty("user.dir");
        provider_controller p = new provider_controller();
        File f6 = new File(path + "/household/8");
        p.closeHousehold("8");
        Assert.assertEquals("Close household successful!", false, f6.exists());
    }


    @Test
    public void check_data_test() throws IOException{
        String path = System.getProperty("user.dir");
        provider_controller p = new provider_controller();
        File_controller ff=new File_controller();
        p.check_data();
        List<String> name=ff.getData_User();
        for(String i:name) {
            File f = new File(path + "/household/"+i+"/today.txt");
            File f1 = new File(path + "/household/"+i+"/day.txt");
            String s=ff.readLastNLine(f1).trim().split(",")[0];
            BufferedReader br1=new BufferedReader(new FileReader(f));
            String s1=br1.readLine().trim().split(",")[0].split(" ")[0];
            Boolean m=null;
            if(s.equals(s1)){
                m=true;
            }else
                m=false;
            Assert.assertEquals("Check the data of day successful!",false,m);
        }
    }

    @Test
    public void check_data_m_test() throws IOException{
        String path=System.getProperty("user.dir");
        provider_controller p=new provider_controller();
        File_controller ff=new File_controller();
        p.check_data_m();
        List<String> name=ff.getData_User();
        for(String i:name){
            File f=new File(path+"/household/"+i+"/day.txt");
            File f1=new File(path+"/household/"+i+"/mouth.txt");
            String s1=ff.readLastNLine(f).trim().split(",")[0];
            String s2=ff.readLastNLine(f1).trim().split(",")[0];
            String s3=s1.substring(0,7);
            Date today = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("dd");
            String D = ft.format(today);
            Boolean m;
            if(D.equals("01")&&!s2.equals(s3)){
                m=true;
            }
            else if(!D.equals("01")&&s2.equals(s3)){
                m=true;
            }
            else
                m=false;

            Assert.assertEquals("Check the data of month successful!",true,m);
        }
    }

    @Test
    public void bill_send_test(){
        String path=System.getProperty("user.dir");
        Bills b=new Bills();
        b.email("1");
        Date today = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");
        String D = ft.format(today);
        System.out.println(D);
        boolean m;
        File f=new File(path+"/bills/1_"+D+".txt");
        if(f.exists()){
            m=true;
        }else
            m=false;
        Assert.assertEquals("Bill send successful!",true,m);
    }

}