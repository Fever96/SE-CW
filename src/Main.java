import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException{
        provider_controller p=new provider_controller();
        p.init();
        JFrame f1=new Welcome();
        f1.setVisible(true);
    }
}
