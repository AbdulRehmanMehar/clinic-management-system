import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;

public class Clinic {
    private String name;
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private ArrayList<Appointment> appointments;

    public Clinic(String name) {
        this.setName(name);
        this.doctors = new ArrayList<Doctor>();
        this.patients = new ArrayList<Patient>();
        this.appointments = new ArrayList<Appointment>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDoctor(Doctor doctor) {
        if(
            doctor != null && 
            !this.doctors.contains(doctor)
        ) this.doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        if(
            patient != null &&
            !this.patients.contains(patient)
        ) this.patients.add(patient);
    }

    public void addAppointment(Appointment appointment) {
        if(
            appointment != null &&
            !this.appointments.contains(appointment)
        ) this.appointments.add(appointment);
    }

    public void removeDoctor(Doctor doctor) {
        if (this.doctors.remove(doctor)) {
            doctor = null;
        }
    }

    public void removePatient(Patient patient) {
        if (this.patients.remove(patient)) {
            patient = null;
        }
    }

    public void removeAppointment(Appointment appointment) {
        if (this.appointments.remove(appointment)) {
            appointment.getDoctor().getAppointments().remove(appointment);
            appointment.getPatient().getAppointments().remove(appointment);
            appointment = null;
        }
    }

    public String getName() {
        return this.name;
    }

    public Doctor[] getDoctors() {
        return Arrays.copyOf(this.doctors.toArray(), this.doctors.size(), Doctor[].class);
    }

    public Patient[] getPatients() {
        return Arrays.copyOf(this.patients.toArray(), this.patients.size(), Patient[].class);
    }

    public Appointment[] getAppointments() {
        return Arrays.copyOf(this.appointments.toArray(), this.appointments.size(), Appointment[].class);
    }

}
