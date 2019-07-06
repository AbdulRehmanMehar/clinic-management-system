import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class Clinic {
    private String name;
    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<Appointment> appointments;

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

    public String getName() {
        return this.name;
    }

    public Doctor[] getDoctors() {
        return (Doctor[]) this.doctors.toArray();
    }

    public Patient[] getPatients() {
        return (Patient[]) this.patients.toArray();
    }

    public Appointment[] getAppointments() {
        return (Appointment[]) this.appointments.toArray();
    }

}
