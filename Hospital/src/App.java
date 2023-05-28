import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


class test{
     private ArrayList<Doctor> ar;
    test(){
        ar = new ArrayList<>();
    }
    public ArrayList<Doctor> getAr() {
        return ar;
    }
    
}
public class App {
    public static void main(String[] args) throws Exception {
         Hospital hospital = new Hospital("olymp", 911);
         new DataCenter();
         DataCenter.loadAll(hospital);
         hospital.menu();
        //  System.out.println(hospital.getDoctors());
        //  System.out.println(hospital.getDoctors().get(1).getID());
        //  System.out.println(Doctor.getIDCreator());
        //  DataCenter.saveDoctor(hospital, new Doctor("fewqfqw", "ff", "ff", "ff", 9213, 100, false, false, "wqefqw", "fewqfeqw"));
        //  System.out.println(1);
        //  DataCenter.saveDoctor(hospital, new Doctor("ftrewrr", "ff", "ff", "ff", 9123, 100, false, false, "wqefqw", "fewqfqw"));
        //  System.out.println(hospital.getDoctors());
        //  DataCenter.remove("doctor", 3);
        //  DataCenter.updateInfo("doctor", "firstName", 1, "amir");
    }
}
