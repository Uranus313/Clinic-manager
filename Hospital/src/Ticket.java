import java.sql.Timestamp;

public abstract class Ticket {
    private Patient patient;
    private Doctor doctor;
    private Timestamp timestamp;
    private long ID;
    public long getID() {
        return ID;
    }
    public void setID(long iD) {
        ID = iD;
    }
    public Ticket(Patient patient, Doctor doctor, Timestamp timestamp) {
        this.patient = patient;
        this.doctor = doctor;
        this.timestamp = timestamp;
    }
    public Patient getPatient() {
        return patient;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    
    
}
