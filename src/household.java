import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class household implements Runnable{
    public String path = System.getProperty("user.dir");
    protected int accNo;
    protected String accName;
    private String Email;
//    provider p=new provider();
    private double gas;
    private double ele;
    Date date=new Date();
    String D=c_time(date);
    // 使用 toString() 函数显示日期时间

    public household(){}

    public void household_set(String D,int accNo, String accName,String email, double gas1,double ele1) {
        this.D=D;
        this.accNo=accNo;
        this.accName=accName;
        this.Email=email;
        this.ele =ele1;
        this.gas =gas1;
    }

    public void init(String D,int accNo, String accName,String email){
        try {
            File f =new File(path+"/household/"+get_accNo());
            if (!f.exists()){
                f.mkdir();
            }

            File f1 = new File(path + "/household/" + get_accNo() + "/today.txt");
            File f2 = new File(path + "/household/" + get_accNo() + "/day.txt");
            File f3 = new File(path + "/household/" + get_accNo() + "/mouth.txt");
            File f4 = new File(path + "/household/" + get_accNo() + "/budget.txt");
            if(!f1.exists()) {
                f1.createNewFile();
                f2.createNewFile();
                f3.createNewFile();
                f4.createNewFile();

                BufferedWriter bw1 = new BufferedWriter(new FileWriter(f1));
                BufferedWriter bw2 = new BufferedWriter(new FileWriter(f2));
                BufferedWriter bw3 = new BufferedWriter(new FileWriter(f3));
                BufferedWriter bw4 = new BufferedWriter(new FileWriter(f4));
                bw1.write(D + "," + String.valueOf(accNo) + "," + accName + "," + email + ",0.0,0.0,0.0,0.0");
                bw2.write("," + String.valueOf(accNo) + "," + accName + "," + email + ",0.0,0.0,0.0,0.0");
                bw3.write("," + String.valueOf(accNo) + "," + accName + "," + email + ",0.0,0.0,0.0,0.0");
                bw4.write("" + "," + "");
                bw1.close();
                bw2.close();
                bw3.close();
                bw4.close();
            }
        }catch(IOException e)
        {
            System.out.println("new household error!");
        }
    }


    //get function
    public int get_accNo() {
        return accNo;};
    public String get_Date(){
        return D;
    }

    public String get_email(){

        return Email;
    }

    public String get_accName() {
        return accName;
    }

    public double get_ele(){

        return ele;
    }
    public double get_gas(){

        return gas;
    }

    //set function

    public void set_AccNo(int accNo){
        this.accNo = accNo;
    }
    public void set_AccName(String accName){
        this.accName = accName;
    }
    public void e_increase(){
        ele=ele+0.5;
    }
    public void g_increase(){
        gas=gas+1.0;
    }

    public double ele_con() throws IOException{
        File f=new File(path+"/tariff.txt");
        BufferedReader br=new BufferedReader(new FileReader(f));
        String s=br.readLine();
        double a=0.0;

        if(s!=null){
            String[] b=s.split(",");
            a=Double.parseDouble(b[0]);
            s=br.readLine();
        }

        BigDecimal b = new BigDecimal(a*get_ele()/240);
        double f1 = b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public double gas_con() throws IOException{
        File f=new File(path+"/tariff.txt");
        BufferedReader br=new BufferedReader(new FileReader(f));
        String s=br.readLine();
        double a=0.0;
        if(s!=null){
            String[] b=s.split(",");
            a=Double.parseDouble(b[1]);
            s=br.readLine();
        }
        BigDecimal b   =   new   BigDecimal(a*get_gas()/240);
        double f1 = b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;

    }
    public String get() throws IOException {
        return D+","+accNo+","+accName+","+Email+","+get_ele()+","+String.valueOf(ele_con())+
                ","+get_gas()+","+String.valueOf(gas_con());
    }

    public String toString(){
        return "this household consume eletric " + get_ele() +"\n"+
                "this household consume gas" +get_gas()+'\n';
    }


    public String c_time(Date date){
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String D=ft.format(date);
        return D;
    }

    public void wr(String s) throws IOException{
        File f=new File(path+"/household/"+get_accNo()+"/"+"today.txt");
        BufferedWriter bw=new BufferedWriter(new FileWriter(f,false));
        bw.write(s);
        bw.newLine();
        bw.close();
    }

    public void set_budget(String i,String[] budget){
        File f=new File(path+"/household/"+i+"/budget.txt");
        try{
            BufferedWriter bw=new BufferedWriter(new FileWriter(f));
            bw.write(budget[0]+","+budget[1]+","+budget[2]+","+budget[3]);
            bw.close();
        }catch (IOException e){
            System.out.println("budget error");
        }
    }

    public String[] get_budget(String i){
        File f=new File(path+"/household/"+i+"/budget.txt");
        String[] a=new String[4];
        try{
            BufferedReader br1=new BufferedReader(new FileReader(f));
            String s=br1.readLine().trim();
            a=s.split(",");
        }catch (IOException r){

        }return a;

    }

    public void run(){
        while(true){
            try {
                Date d=new Date();
//                System.out.println(get());
                D=c_time(d);
                e_increase();
                g_increase();
                wr(get());
                Thread.sleep(15000);
            }catch(InterruptedException|IOException e){
                System.out.println("Thread error");
            }
        }

    }

}

