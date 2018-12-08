import java.io.*;

/**
 * Created by oyh on 2018/5/17.
 */
public class provider extends P_H {
    public String path=System.getProperty("user.dir");
    public void init(){};
    private String password;
    private double tariff_ele;
    private double tariff_gas;
    public provider(){

    }
    public provider(String password,Double tariff_ele,Double tariff_gas){
        this.password=password;
        this.tariff_ele=tariff_ele;
        this.tariff_gas=tariff_gas;
    }

    public void setTariff_ele(Double tariff_ele){

        this.tariff_ele=tariff_ele;
    }

    public Double getTariff_ele(){
        return tariff_ele;
    }

    public void setTariff_gas(Double tariff_gas){

        this.tariff_gas=tariff_gas;
    }
    public Double getTariff_gas(){

        return tariff_gas;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passßword) {
        this.password = passßword;
    }
}
