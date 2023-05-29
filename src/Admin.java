import java.util.Random;

public class Admin extends Person{


    private static String adminPassword="17";
    private static long IDCreator=1;
    public Admin(String firstName, String lastName, String address, String birthDate, long personalID, String password) {
        super(firstName, lastName, address, birthDate, personalID, password);
        this.setID(IDCreator);
        IDCreator++;
    }

    public Admin(long id, long randomID, String firstName, String lastName, String address, String birthDate, long personalID, String password) {
        super(id, randomID, firstName, lastName, address, birthDate, personalID, password);
    }

    public void randomGenerator(Hospital hospital) {
        boolean checker = true;
        while(checker){
            this.setRandomID(new Random().nextLong());
            checker = false;
            for(Admin i: hospital.getAdmins()){
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

    public static String getAdminPassword() {
        return adminPassword;
    }

    public static void setAdminPassword(String adminPassword) {
        Admin.adminPassword = adminPassword;
    }

    @Override
    public void menu() {

    }
}
