import java.util.Date;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Doctor extends Role {
    private String specialization;
    private ArrayList<Availability> availabilities;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    public Doctor(Clinic clinic, Person person, String specialization) {
        super(clinic, person);
        this.setSpecialization(specialization);
        this.availabilities = new ArrayList<Availability>();
        // clinic.addDoctor(this);
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public void addAppointment(Appointment appointment) {
        if(!super.appointments.contains(appointment)) {
            boolean available = false;
            Iterator<Availability> itr = this.availabilities.iterator();

            while(itr.hasNext()) {
                Availability availability = itr.next();
                if(
                    availability.getDay().equals(appointment.getDay()) &&
                    availability.getTimeIn() - appointment.getTimeStart() <= 0 &&
                    availability.getTimeOut() - appointment.getTimeEnd() >= 0
                ) available = true;
            }

            if (available) 
                super.appointments.add(appointment);
        }

    }

    public void addAvailability(Availability availability) {
        if (!this.availabilities.contains(availability)) {
            this.availabilities.add(availability);
            availability.setDoctor(this);
        }
    }

    public void removeAvailability(Availability availability) {
        if (this.availabilities.remove(availability)) {
            for (Appointment appointment : super.getAppointments()) {
                if (
                    appointment.getDay().equals(availability.getDay()) &&
                    appointment.getTimeStart() >= availability.getTimeIn() && 
                    appointment.getTimeEnd() <= availability.getTimeOut()
                ) {
                    super.getClinic().removeAppointment(appointment);
                }
            }
            availability = null;
        }
    }

    public boolean hasAvailability(String day) {
        for (Availability availability : this.availabilities) {
            if (availability.getDay().equals(day)) return true;
        }
        return false;
    }

    public boolean hasAvailability(String day, String timeStart, String timeEnd) {
        try{    
            for (Availability availability : this.availabilities) {
                System.out.println(availability.getTimeIn() +" "+ this.sdf.parse(timeStart).getTime());
                System.out.println(availability.getTimeOut() + " " + this.sdf.parse(timeEnd).getTime());
                if (
                        availability.getDay().equals(day)
                        && availability.getTimeIn() - this.sdf.parse(timeStart).getTime()<= 0
                        && availability.getTimeOut() - this.sdf.parse(timeEnd).getTime() >= 0
                )
                    return true;
            }
            return false;
        } catch(Exception e) {
            System.out.println("Exception " + e + " occured in Doctor.hasAvailability(String, String, String): void");
            return false;
        }
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
