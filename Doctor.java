import java.util.List;
import java.util.ArrayList;

public class Doctor extends Role {
    private String specialization;
    private List<Appointment> appointments;
    private List<Availability> availabilities;

    public Doctor(Person person, String specialization) {
        super(person);
        this.setSpecialization(specialization);
        this.appointments = new ArrayList<Appointment>();
        this.availabilities = new ArrayList<Availability>();
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public String getSpecialization() {
        return this.specialization;
    }

    public String getRole() {
        return "Doctor";
    }

}
