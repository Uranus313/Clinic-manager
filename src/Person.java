import java.sql.Date;

public abstract class Person implements GlobalFunctions  {
    private String firstName;
    private String lastName;
    private String address;
    private String birthDate;
    private long personalID;
    private long ID;
    private String password;
    private long randomID;
    public Person(String firstName, String lastName, String address, String birthDate, long personalID,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthDate = birthDate;
        this.personalID = personalID;
        this.password = password;
    }
    public Person(long id,long randomID,String firstName, String lastName, String address, String birthDate, long personalID,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthDate = birthDate;
        this.personalID = personalID;
        this.password = password;
        this.ID= id;
        this.randomID = randomID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public long getPersonalID() {
        return personalID;
    }
    public void setPersonalID(long personalID) {
        this.personalID = personalID;
    }
    public long getID() {
        return ID;
    }
    public void setID(long iD) {
        ID = iD;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public long getRandomID() {
        return randomID;
    }
    public void setRandomID(long randomID) {
        this.randomID = randomID;
    }

}
