import java.util.Random;

public class Nurse extends ClinicMember {
    String placeholder;

    private static long IDCreator=1;
    public Nurse(String firstName, String lastName, String address, String birthDate, long personalID, long salary,
            boolean nightShift, boolean organce,String password) {
        super(firstName, lastName, address, birthDate, personalID, salary, nightShift, organce, password);
        this.setID(IDCreator);
        IDCreator++;
    }
    
    public void randomGenerator(Hospital hospital) {
        boolean checker = true;
       while(checker){
        this.setRandomID(new Random().nextLong());
        checker = false;
        for(Nurse i: hospital.getNurses()){
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
