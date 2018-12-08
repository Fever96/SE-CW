import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.valueOf;

/**
 * Created by oyh on 2018/5/17.
 */
public class File_controller {
    public String path=System.getProperty("user.dir");
    public File_controller(){

    }
    //获取目录下所有文件名
    public ArrayList<String> getFiles(String path) {
        ArrayList<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                System.out.print("!");
            }
            if (tempList[i].isDirectory()) {
                String[] s=tempList[i].toString().split("/");
                files.add(s[s.length-1]);
            }
        }
        return files;
    }


    //读取最后一行数据
    public String readLastNLine(File file)
    {
        String result = new String();
        long count = 0;
        if (!file.exists() || file.isDirectory() || !file.canRead())
        {
            return null;
        }
        RandomAccessFile fileRead = null;
        try
        {
            fileRead = new RandomAccessFile(file, "r");
            long length = fileRead.length();
            if (length == 0L)
            {
                return result;
            }
            else
            {
                long pos = length - 1;
                while (pos > 0)
                {
                    pos--;
                    fileRead.seek(pos);
                    if (fileRead.readByte() == '\n')
                    {
                        String line = fileRead.readLine();
                        result=line;
                        System.out.println(line);
                        count++;
                        if (count == 1L)
                        {
                            break;
                        }
                    }
                }
                if (pos == 0)
                {
                    fileRead.seek(0);
                    result=fileRead.readLine();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fileRead != null)
            {
                try
                {
                    fileRead.close();
                }
                catch (Exception e)
                {
                }
            }
        }

        return result;
    }
    //获取用户id
    public List<String> getData_User() {
        List<String> s = new ArrayList<String>();
        File f=new File(path+"/household/");
        File[] f2=f.listFiles();
        for(File f1:f2){
            while(!f1.isFile()){
                String a=new String(f1.toString());
                String[] a1=a.split("/");
                String aa=a1[a1.length-1];
                s.add(aa);
                break;
            }
        }
        return s;
    }
    //current time
    public String c_time(Date date){
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String D=ft.format(date);
        return D;
    }

    //get Data
    public List<String> getData(String i) {
        File f = new File(path + "/household/");
        File[] x = f.listFiles();
        List<String> s = new ArrayList<String>();
        for (File a : x) {
            if (a.isDirectory()) {
                FileReader fr;
                File file = new File(a + "/"+i+".txt");
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

    public List<String> getData(String i,String m) {
        File f = new File(path + "/household/"+i+"/"+m+".txt");

        File[] x = f.listFiles();
        List<String> s = new ArrayList<String>();
        if(f.exists()){
            FileReader fr;
            int b;
            StringBuffer sb=new StringBuffer();
            try{
                fr=new FileReader(f);
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
        return s;
    }
    public int get_hours(){
        Date today=new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String D=ft.format(today);
        String D1=D.split(" ")[1].split(":")[0];
        int a=valueOf(D1);
        if(a<6||a>=20){
            return 1;
        }else if(a>=6&&a<13){
            return 2;
        }else if(a>=13&&a<20){
            return 3;
        }
        return 0;
    }

    //获取所有用户Name
    public String get_Name(String i){
        String s="";
        File f=new File(path+"/household/"+i+"/today.txt");
        try{
            BufferedReader br1=new BufferedReader(new FileReader(f));
            String a=br1.readLine();
            s=a.split(",")[2];
        }catch (IOException e){
            System.out.println("get_Name error!");
        }
        return s;
    }


}
