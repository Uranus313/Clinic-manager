import java.sql.Timestamp;

public class Visit extends Ticket {
    private String drugPrescription;
    private String orders;
    private static long IDCreator=1;
    public Visit(Patient patient, Doctor doctor, Timestamp timestamp, String drugPrescription, String orders) {
        super(patient, doctor, timestamp);
        this.drugPrescription = drugPrescription;
        this.orders = orders;
        this.setID(IDCreator);
        IDCreator++;
    }
    public String getDrugPrescription() {
        return drugPrescription;
    }
    public String getOrders() {
        return orders;
    }
    
    
}
