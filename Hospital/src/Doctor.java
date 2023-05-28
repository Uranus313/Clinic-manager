import java.util.ArrayList;
import java.util.Random;



public class Doctor extends ClinicMember {
    private String profession;
    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    private static long IDCreator=1;
    public Doctor(String firstName, String lastName, String address, String birthDate, long personalID, long salary,
            boolean nightShift, boolean organce, String profession,String password) {
        super(firstName, lastName, address, birthDate, personalID, salary, nightShift, organce, password);
        this.profession = profession;
        this.setID(IDCreator);
        IDCreator++;
    }
    

    public Doctor(long ID, long randomID, String firstName, String lastName, String address, String birthDate,
            long personalID, long salary, boolean nightShift, boolean organce, String password, String profession) {
        super(ID, randomID, firstName, lastName, address, birthDate, personalID, salary, nightShift, organce, password);
        this.profession = profession;
    }
    
    public ArrayList<Reserve> getReserves(Hospital hospital) {
        ArrayList<Reserve> reserves= new ArrayList<>();
        for(Reserve i : hospital.getReserves()){
            if(i.getDoctor()==this){
                reserves.add(i);
            }
        }
        return reserves;
    }
    public ArrayList<Visit> getVisits(Hospital hospital) {
        ArrayList<Visit> visits= new ArrayList<>();
        for(Visit i : hospital.getVisits()){
            if(i.getDoctor()==this){
                visits.add(i);
            }
        }
        return visits;
    }      
    public ArrayList<Patient> getPatients(Hospital hospital) {
        ArrayList<Patient> patients= new ArrayList<>();
        for(Visit i : hospital.getVisits()){
            boolean checker = false;
            if(i.getDoctor()==this){
                for(Patient j : patients){
                    if(i.getPatient()==j){
                        checker = true;
                        break;
                    }
                    if(checker){
                        continue;
                    }
                    patients.add(i.getPatient());
                }
            }
        }
        return patients;
    }
    public void randomGenerator(Hospital hospital) {
        boolean checker = true;
       while(checker){
        this.setRandomID(new Random().nextLong());
        checker = false;
        for(Doctor i: hospital.getDoctors()){
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
    @Override
    public void menu() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'menu'");
    }
    public static long getIDCreator() {
        return IDCreator;
    }
    public static void setIDCreator(long iDCreator) {
        IDCreator = iDCreator;
    }        
    
}
