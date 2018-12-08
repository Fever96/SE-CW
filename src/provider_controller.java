
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class provider_controller{
    public String path=System.getProperty("user.dir");
    public ArrayList<household> p=new ArrayList<household>();
    private household xxx;
    private provider m=this.Get_provider();
    String data;
    private File_controller ff=new File_controller();
    private Bills bs;

    public provider_controller() {
    }

    public void init(){
        try{
            init_tariff();
            loaddata1();
            init_thread();
            check_data();
            check_data_m();
            send_bills_check();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //get provider
    public provider Get_provider(){
        provider s=new provider();
        return s;
    }

//tariff operation
    //init tariff
    public void init_tariff(){
        //Double e=3.88;
        //Double g=14.6;
        //m.setTariff_ele(e);
        //m.setTariff_gas(g);
        File f=new File(path+"/tariff.txt");
        try{
            BufferedReader bw=new BufferedReader(new FileReader(f));
            String s=bw.readLine();
            String[] ss=s.trim().split(",");
            Double e=Double.parseDouble(ss[0]);
            Double g=Double.parseDouble(ss[1]);
            m.setTariff_ele(e);
            m.setTariff_gas(g);
            bw.close();
        }catch (IOException r){

        }
    }

    public String[] view_tariff() throws IOException{
        String[] a=new String[2];
        Double a1=m.getTariff_ele();
        Double a2=m.getTariff_gas();
        a[0]=String.valueOf(a1);
        a[1]=String.valueOf(a2);
        return a;
    }

    public void set_tariff(Double ta_ele,Double ta_gas) throws IOException{
        m.setTariff_ele(ta_ele);
        m.setTariff_gas(ta_gas);
        File f=new File(path+"/tariff.txt");
        BufferedWriter bw=new BufferedWriter(new FileWriter(f,false));
        String t_ele=String.valueOf(ta_ele);
        String t_gas=String.valueOf(ta_gas);
        bw.write(t_ele+","+t_gas);
        bw.close();
    }
    public void loaddata1() throws IOException {
        Date today=new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String D=ft.format(today);
        //ArrayList<String> F= getFiles(path+"/household/"+xxx.get_accNo()+"/day.txt");
        for (String i : ff.getFiles(path + "/household/")) {

            File f = new File(path + "/household/" +i+ "/today.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            while (s != null) {
                String[] s1 = s.split(",");
                int No = Integer.parseInt(s1[1]);
                double e = Double.parseDouble(s1[4]);
                double g = Double.parseDouble(s1[6]);
                String a1=s1[0].split(" ")[0];
                String a2=D.split(" ")[0];
                if(a1.equals(a2)){
//                System.out.println();
                    household h1 = new household();
                    h1.household_set(s1[0], No, s1[2], s1[3], e, g);
                    p.add(h1);
                }else{
                    household h1=new household();
                    h1.household_set(D,No,s1[2],s1[3],0.0,0.0);
                    p.add(h1);
                }
                s = br.readLine();
            }
        }
    }

    public boolean check_budget(String i){
        File f1=new File(path+"/household/"+i+"/today.txt");
        File f2=new File(path+"/household/"+i+"/budget.txt");
        String[] a1=new String[7];
        String[] a2=new String[7];
        try{
            BufferedReader br1=new BufferedReader(new FileReader(f1));
            BufferedReader br2=new BufferedReader(new FileReader(f2));
            a1=br1.readLine().split(",");
            a2=br2.readLine().split(",");
            Double c1=Double.parseDouble(a1[4]);
            Double c2=Double.parseDouble(a1[5]);
            Double c3=Double.parseDouble(a1[6]);
            Double c4=Double.parseDouble(a1[7]);
            Double d1=Double.parseDouble(a2[0]);
            Double d2=Double.parseDouble(a2[1]);
            Double d3=Double.parseDouble(a2[2]);
            Double d4=Double.parseDouble(a2[3]);
            br1.close();
            br2.close();

            if(c1<d1&&c2<d2&&c3<d3&&c4<d4){
                return true;
            }else {
                return false;
            }

        }catch (IOException r){

        }return false;
    }

    public void init_thread(){
        for(household x:p){
            Runnable r=x;
            Thread t=new Thread(r);
            t.start();
        }
    }

    public void run_thread1(household x){
        Runnable r=x;
        Thread t=new Thread(r);
        t.start();
    }

    public void newHousehold(household h) throws IOException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        String s=sdf.format(date);
        String[] a=s.split("-");
        String ss=a[0]+"-"+a[1];

        p.add(h);
        File f=new File(path+"/household/"+h.get_accNo());
        File f2=new File(path+"/household/"+h.get_accNo()+"/today.txt");
        File f3=new File(path+"/household/"+h.get_accNo()+"/day.txt");
        File f4=new File(path+"/household/"+h.get_accNo()+"/mouth.txt");
        File f5=new File(path+"/household/"+h.get_accNo()+"/bugdet.txt");
        if(!f.exists())
        {
            f.mkdir();
            f2.createNewFile();
            f3.createNewFile();
            f4.createNewFile();
            f5.createNewFile();
        }
        //File f=new File(path+"/household/"+h.get_accNo()+"");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f2, true));
        //System.out.print(n.get(1));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(f3,true));
        BufferedWriter bw3 = new BufferedWriter(new FileWriter(f4,true));
        BufferedWriter bw4 = new BufferedWriter(new FileWriter(f5));
        bw2.write(s+","+h.get_accNo()+","+h.get_accName()+","+h.get_email()+","+"0.0,0.0,0.0,0.0");
        bw2.newLine();
        bw.write(h.get());
        bw.newLine();
        bw3.write(ss+","+h.get_accNo()+","+h.get_accName()+","+h.get_email()+","+"0.0,0.0,0.0,0.0");
        bw4.write(" , , , ");
        bw2.close();
        bw3.close();
        bw4.close();
        bw.close();
        run_thread1(h);
    }

    //close household and delete the files;
    public void closeHousehold(String i) throws IOException{
        File f=new File(path+"/household/"+i);
        File[] f1=f.listFiles();
        for (File f2 : f1) {
            f2.delete();
        }
        f.delete();
    }

    public List<String> getData_User() {
        List<String> s = new ArrayList<String>();
        File f=new File(path+"/household/");
        File[] f2=f.listFiles();
        for(File f1:f2){
            while(!f1.isFile()){
                String a=new String(f1.toString());
                String aa=a.substring(a.length()-1,a.length());
                s.add(aa);
///                System.out.println(aa);
                break;
            }
        }
        return s;
    }

    public void check_data() throws IOException{
        Date cur = new Date();
        for(household h:p) {
            File f = new File(path + "/household/" + h.get_accNo() + "/today.txt");
            File f2= new File(path+"/household/"+h.get_accNo()+"/day.txt");

            BufferedWriter bw2 = new BufferedWriter(new FileWriter(f2, true));
            BufferedReader br = new BufferedReader(new FileReader(f));
            String s = br.readLine();
            String[] n = s.split(",");
//            System.out.println("!!!");
            String[] a1=h.c_time(cur).split(" ");
            String[] a2=n[0].split(" ");
            while(s!=null) {
                if (!a1[0].equals(a2[0])) {
                    bw2.write(a2[0]+","+n[1]+","+n[2]+","+n[3]+","+n[4]+","+n[5]+","+n[6]+","+n[7]+"\n");
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f, false));
                    bw.write("");
                    bw.close();
                }
                s=br.readLine();
                br.close();
            }
            bw2.close();
        }
    }

    public void check_data_m() throws IOException{
        Date cur = new Date();
        for (household x:p){
            File f=new File(path+"/household/"+x.get_accNo()+"/day.txt");
            File f2=new File(path+"/household/"+x.get_accNo()+"/mouth.txt");
//            BufferedWriter bw1=new BufferedWriter(new FileWriter(f,true));
            BufferedReader br2=new BufferedReader(new FileReader(f2));
            String m1= br2.readLine();
            List<String> a=new ArrayList<String>();
            String day_last=ff.readLastNLine(f);
            if(m1==null){
                br2.close();
                BufferedWriter bw2=new BufferedWriter(new FileWriter(f2,true));
                String[] s=day_last.trim().split(",");
                String[] date=s[0].split(" ")[0].split("-");
                bw2.write(date[0]+"-"+date[1]+","+s[1]+","+s[2]+","+s[3]+","+s[4]+","+s[5]+","+s[6]+","+s[7]+"\n");
                bw2.close();
                a=null;
            }
            while(m1!=null){
                a.add(m1);
                m1=br2.readLine();
            }

            if(a!=null){
                f2.delete();
                f2.createNewFile();
                BufferedWriter bw2=new BufferedWriter(new FileWriter(f2));
                String aa=a.get(a.size()-1);
                String[] s1=aa.split(",");
                String[] s2=day_last.split(",");
                String[] s3=s1[0].split(" ");
                String[] s4=s2[0].split(" ");
                String s5=s4[0].split("-")[0]+"-"+s4[0].split("-")[1];
                if(s3[0].equals(s5)) {
                    for(int i=0;i<a.size()-1;i++){
                        bw2.write(a.get(i)+"\n");
                    }
                    Double a1 = Double.parseDouble(s1[4]);
                    Double a2 = Double.parseDouble(s1[5]);
                    Double a3 = Double.parseDouble(s1[6]);
                    Double a4 = Double.parseDouble(s1[7]);
                    Double b1 = Double.parseDouble(s2[4]);
                    Double b2 = Double.parseDouble(s2[5]);
                    Double b3 = Double.parseDouble(s2[6]);
                    Double b4 = Double.parseDouble(s2[7]);
                    BigDecimal d1   =   new   BigDecimal(a1+b1);
                    double e1 = d1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    BigDecimal d2   =   new   BigDecimal(a2+b2);
                    double e2 = d2.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    BigDecimal d3   =   new   BigDecimal(a3+b3);
                    double e3 = d3.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    BigDecimal d4   =   new   BigDecimal(a4+b4);
                    double e4 = d4.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    String c1 = String.valueOf(e1);
                    String c2 = String.valueOf(e2);
                    String c3 = String.valueOf(e3);
                    String c4 = String.valueOf(e4);
                    bw2.write(s5+","+s1[1]+","+s1[2]+","+s1[3]+","+c1+","+c2+","+c3+","+c4+"\n");
                    bw2.close();
                }
                else{
                    for(int i=0;i<a.size();i++){
                        bw2.write(a.get(i)+"\n");
                    }
                    bw2.write(s5+","+s1[1]+","+s1[2]+","+s1[3]+","+s2[4]+","+s2[5]+","+s2[6]+","+s2[7]+"\n");
                    bw2.close();
                }
            }
        }
    }


    public String get_password() {
        String s="";
        File f = new File(path + "/password.txt");
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(f));
            s = br1.readLine().trim();
        } catch (IOException e){

        }
        return s;
    }

    public void send_bills_check(){
        bs=new Bills();
        Date today=new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String D=ft.format(today);
        String[] D1=D.split("-");
        if(D1[2].equals("01")){
            List<String> m1=ff.getData_User();
            for (int i=0;i<m1.size();i++){
                bs.email((m1.get(i)));
            }
        }
    }

}
