public abstract class ClinicMember extends Person{
    private long salary;
    private boolean nightShift;
    private boolean organce;
    //idc
    public ClinicMember(String firstName, String lastName, String address, String birthDate, long personalID,
            long salary, boolean nightShift, boolean organce,String password) {
        super(firstName, lastName, address, birthDate, personalID, password);
        this.salary = salary;
        this.nightShift = nightShift;
        this.organce = organce;
    }
    public ClinicMember(long ID,long randomID,String firstName, String lastName, String address, String birthDate, long personalID,
            long salary, boolean nightShift, boolean organce,String password) {
        super(ID,randomID,firstName, lastName, address, birthDate, personalID, password);
        this.salary = salary;
        this.nightShift = nightShift;
        this.organce = organce;
    }
    public long getSalary() {
        return salary;
    }
    public void setSalary(long salary) {
        this.salary = salary;
    }
    public boolean isNightShift() {
        return nightShift;
    }
    public void setNightShift(boolean nightShift) {
        this.nightShift = nightShift;
    }
    public boolean isOrgance() {
        return organce;
    }
    public void setOrgance(boolean organce) {
        this.organce = organce;
    }
        
}
