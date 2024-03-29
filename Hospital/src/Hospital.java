import java.util.ArrayList;
import java.util.Scanner;

public class Hospital {
    private String name;
    private long phoneNumber;
    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;
    private ArrayList<Reserve> reserves;
    private ArrayList<Visit>visits;
    private ArrayList<Nurse>nurses;
    private ArrayList<Staff>staff;
    public Hospital(String name, long phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        patients= new ArrayList<>();
        doctors= new ArrayList<>();
        reserves= new ArrayList<>();
        visits= new ArrayList<>();
        nurses=new ArrayList<>();
        staff=new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public ArrayList<Patient> getPatients() {
        return patients;
    }
    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }
    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }
    public ArrayList<Reserve> getReserves() {
        return reserves;
    }
    public void setReserves(ArrayList<Reserve> reserves) {
        this.reserves = reserves;
    }
    public ArrayList<Visit> getVisits() {
        return visits;
    }
    public void setVisits(ArrayList<Visit> visits) {
        this.visits = visits;
    }
    public ArrayList<Nurse> getNurses() {
        return nurses;
    }
    public void setNurses(ArrayList<Nurse> nurses) {
        this.nurses = nurses;
    }
    public ArrayList<Staff> getStaff() {
        return staff;
    }
    public void setStaff(ArrayList<Staff> staff) {
        this.staff = staff;
    }
    public  void menu(){
        Scanner intScanner = new Scanner(System.in);
        System.out.println("1)patient sign up");
        while(true){
            try {
            int choice = intScanner.nextInt();
            switch (choice) {
                case 1:
                    Patient.signUp(this);
                    break;
            
                default:
                    break;
            }
            } catch (Exception e) {
            // TODO: handle exception
            }
        }
    } 
    
    
}
