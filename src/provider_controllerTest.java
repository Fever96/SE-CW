import org.junit.*;
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
 * Created by oyh on 2018/5/18.
 */
public class provider_controllerTest {
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

    @org.junit.Test
    public void newhousehold_test() throws IOException {
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

    }

    @org.junit.Test
    public void closeHousehold_test() throws IOException{
        String path = System.getProperty("user.dir");
        provider_controller p = new provider_controller();
        //new a household
        household h = new household();
        Date today = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String D = ft.format(today);
        h.household_set(D, 8, "A", "ou@gmail.com", 0.0, 0.0);
        p.newHousehold(h);
        File f = new File(path + "/household/8");
        Assert.assertEquals("Create new household successful!", true, f.exists());
                //try to close this household
        p.closeHousehold("8");
        Assert.assertEquals("Close household successful!", false, f.exists());
    }

    @org.junit.Test
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


}