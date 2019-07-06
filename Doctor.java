import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;

public class Doctor extends Role {
    private String specialization;
    private ArrayList<Availability> availabilities;

    public Doctor(Clinic clinic, Person person, String specialization) {
        super(clinic, person);
        this.setSpecialization(specialization);
        this.availabilities = new ArrayList<Availability>();
        clinic.addDoctor(this);
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
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

    @Override
    public void cancelAppointment(Appointment appointment) {
        if (super.appointments.remove(appointment)) {
            this.getClinic().removeAppointment(appointment);
        }
    }

    public void addAvailability(Availability availability) {
        if (!this.availabilities.contains(availability))
            this.availabilities.add(availability);
    }

    public void removeAvailability(Availability availability) {
        if (this.availabilities.remove(availability))
            availability = null;
    }

    public Availability[] getAvailabilities() {
        return Arrays.copyOf(this.availabilities.toArray(), this.availabilities.size(), Availability[].class);
    }

    public String getSpecialization() {
        return this.specialization;
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

}
