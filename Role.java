import java.util.ArrayList;

public abstract class Role {
    private Clinic clinic;
    private Person person;
    protected ArrayList<Appointment> appointments;

    public Role(Clinic clinic, Person person) {
        this.setClinic(clinic);
        this.setPerson(person);
        this.appointments = new ArrayList<Appointment>();
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
        if (clinic != null) {
            clinic.addRole(this);
        }
    }

    public void setPerson(Person person) {
        this.person = person;
        if (person != null) {
            person.addRole(this);
        }
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

    public void cancelAppointment(Appointment appointment) {
        this.getClinic().removeAppointment(appointment);
    }

    public abstract String getRole();
    
    public abstract void addAppointment(Appointment appointment);

}
