import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class Doctor extends Role {
    private String specialization;
    private List<Availability> availabilities;

    public Doctor(Clinic clinic, Person person, String specialization) {
        super(clinic, person);
        this.setSpecialization(specialization);
        super.appointments = new ArrayList<Appointment>();
        this.availabilities = new ArrayList<Availability>();
        clinic.addDoctor(this);
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void addAppointment(Appointment appointment) {
        boolean available = false;
        Iterator<Availability> itr = this.availabilities.iterator();

        while(itr.hasNext()) {
            Availability availability = itr.next();
            if(
                availability.getDay().matches(appointment.getDay()) &&
                availability.getTimeIn() - appointment.getTimeStart() > 0 &&
                availability.getTimeOut() - appointment.getTimeEnd() < 0
            ) available = true;
        }

        if (available && !super.appointments.contains(appointment)) 
            super.appointments.add(appointment);

    }

    public void addAvailability(Availability availability) {
        if (!this.availabilities.contains(availability))
            this.availabilities.add(availability);
    }

    public Appointment[] getAppointments() {
        return (Appointment[]) super.appointments.toArray();
    }

    public Availability[] getAvailabilities() {
        return (Availability[]) this.availabilities.toArray();
    }

    public String getSpecialization() {
        return this.specialization;
    }

    public String getRole() {
        return "Doctor";
    }

}
