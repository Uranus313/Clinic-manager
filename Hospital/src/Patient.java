import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Patient extends Person {
    private static long IDCreator=1;
    public Patient(String firstName, String lastName, String address, String birthDate, long personalID,String password) {
        super(firstName, lastName, address, birthDate, personalID, password);
        this.setID(IDCreator);
        IDCreator++;
    }
    public Patient(long id,long randomid,String firstName, String lastName, String address, String birthDate, long personalID,String password) {
        super(id,randomid,firstName, lastName, address, birthDate, personalID, password);
    }
    public ArrayList<Reserve> getReserves(Hospital hospital) {
        ArrayList<Reserve> reserves= new ArrayList<>();
        for(Reserve i : hospital.getReserves()){
            if(i.getPatient()==this){
                reserves.add(i);
            }
        }
        return reserves;
    }
    public ArrayList<Visit> getVisits(Hospital hospital) {
        ArrayList<Visit> visits= new ArrayList<>();
        for(Visit i : hospital.getVisits()){
            if(i.getPatient()==this){
                visits.add(i);
            }
        }
        return visits;
    }
    public void randomGenerator(Hospital hospital) {
        boolean checker = true;
       while(checker){
        this.setRandomID(new Random().nextLong());
        checker = false;
        for(Patient i: hospital.getPatients()){
            if(i==this){
                continue;
            }
            if(i.getRandomID()==this.getRandomID()|| this.getRandomID()<0){
                checker = true;
                break;
            }
        }
       }
    }
    public static long getIDCreator() {
        return IDCreator;
    }
    public static void setIDCreator(long iDCreator) {
        IDCreator = iDCreator;
    }
    @Override
    public void menu() {
       Scanner intReader = new Scanner(System.in);
       while(true){
       }
       
    }     
    public static void signUp(Hospital hospital){
        String firstName;
        String lastName;
        String address;
        String birthDate;
        long personalID=0;
        String password;
        Scanner longReader = new Scanner(System.in);
        Scanner stringReader = new Scanner(System.in);
        System.out.println("pls enter your first name");
        firstName = stringReader.nextLine();
        System.out.println("pls enter your last name");
        lastName = stringReader.nextLine();
        System.out.println("pls enter your address");
        address = stringReader.nextLine();
        System.out.println("pls enter your birthDate");
        birthDate = stringReader.nextLine();
        Boolean checker = true;
        while(checker){
            checker = false;
            System.out.println("pls enter your personal ID number");
            personalID = longReader.nextLong();
            for(Patient i: hospital.getPatients()){
                if(i.getRandomID()==personalID || personalID<0){
                    System.out.println("this personal ID is already in the system");
                    checker = true;
                    break;
                }
            }
        }
        System.out.println("pls enter your password");
        password = stringReader.nextLine();
        Patient patient = new Patient(firstName, lastName, address, birthDate, personalID, password);
        patient.randomGenerator(hospital);
        hospital.getPatients().add(patient);
        DataCenter.savePatient(hospital, patient);
        stringReader.close();
        longReader.close();
    }     
}
