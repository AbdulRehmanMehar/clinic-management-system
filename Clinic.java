import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class Clinic {
    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<Appointment> appointments;

    public Clinic() {
        this.doctors = new ArrayList<Doctor>();
        this.patients = new ArrayList<Patient>();
        this.appointments = new ArrayList<Appointment>();
    }

    public void addDoctor(Doctor doctor) {
        this.doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public List<Doctor> getDoctors() {
        return this.doctors;
    }

    public List<Patient> getPatients() {
        return this.patients;
    }

    public List<Appointment> getAppointments() {
        return this.appointments;
    }

}
