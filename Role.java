public abstract class Role {
    private Clinic clinic;
    private Person person;
    protected List<Appointment> appointments;

    public Role(Clinic clinic, Person person) {
        this.person = person;
        this.clinic = clinic;
    }

    public Clinic getClinic() {
        return this.clinic;
    }

    public Person getPerson() {
        return this.person;
    }

    public abstract String getRole();
    
    public abstract void addAppointment();

    public abstract Appointment[] getAppointments();
}
