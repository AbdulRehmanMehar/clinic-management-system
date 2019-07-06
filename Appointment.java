import java.util.Date;
import java.util.Arrays;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Appointment {
    private String day;
    private Date timeEnd;
    private Date timeStart;
    private Clinic clinic;
    private Doctor doctor;
    private Patient patient;
    private ArrayList<Medicine> medicines;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    public Appointment(Clinic clinic, Doctor doctor, Patient patient, String day, String timeStart, String timeEnd) {
        this.setDay(day);
        this.setTimeStart(timeStart);
        this.setTimeEnd(timeEnd);
        this.setClinic(clinic);
        this.setDoctor(doctor);
        this.setPatient(patient);
        this.medicines = new ArrayList<Medicine>();
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
        clinic.addAppointment(this);
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        doctor.addAppointment(this);
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        patient.addAppointment(this);
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTimeStart(String timeStart) {
        try{
            this.timeStart = this.sdf.parse(timeStart);
        }catch(Exception e) {
            System.out.println("Time Date parse Exception " + e + " in setTimeStart => Appointment.");
        }
    }

    public void setTimeEnd(String timeEnd) {
        try{
            this.timeEnd = this.sdf.parse(timeEnd);
        }catch(Exception e) {
            System.out.println("Time Date parse Exception " + e + " in setTimeEnd => Appointment.");
        }
    }

    public void addMedicine(Medicine medicine) {
        if (
            medicine != null &&
            !this.medicines.contains(medicine)
        ) this.medicines.add(medicine);
    }

    public void removeMedicine(Medicine medicine) {
        if (this.medicines.contains(medicine)){
            this.medicines.remove(medicine);
            medicine = null;
        }
    }

    public Medicine[] getMedicines() {
        return Arrays.copyOf(this.medicines.toArray(), this.medicines.size(), Medicine[].class);
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public Clinic getClinic() {
        return this.clinic;
    }

    public String getDay() {
        return this.day;
    }

    public long getTimeStart() {
        return this.timeStart.getTime();
    }

    public long getTimeEnd() {
        return this.timeEnd.getTime();
    }

}
