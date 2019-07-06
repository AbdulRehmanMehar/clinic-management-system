import java.util.ArrayList;

public abstract class Role {
    private Clinic clinic;
    private Person person;
    protected ArrayList<Appointment> appointments;

    public Role(Clinic clinic, Person person) {
        this.person = person;
        this.clinic = clinic;
        this.appointments = new ArrayList<Appointment>();
    }

    public Clinic getClinic() {
        return this.clinic;
    }

    public Person getPerson() {
        return this.person;
    }

    public ArrayList<Appointment> getAppointments() {
        return this.appointments;
    }

    public abstract String getRole();
    
    public abstract void addAppointment(Appointment appointment);

    public abstract void cancelAppointment(Appointment appointment);
}
