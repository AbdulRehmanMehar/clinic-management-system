import java.util.Date;
import java.text.SimpleDateFormat;

public class Availability {
    private String day;
    private Date timeIn;
    private Date timeOut;
    private Doctor doctor;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    public Availability(Doctor doctor, String day, String timeIn, String timeOut) {
        this.setDay(day);
        this.setDoctor(doctor);
        this.setTimeIn(timeIn);
        this.setTimeOut(timeOut);
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        if (doctor != null)
            doctor.addAvailability(this);
    }

    public void setDay(String day) {
        if (this.doctor.getAppointments().size() > 0 && this.day != null && this.day != day) {
            for (Appointment appointment : this.doctor.getAppointments()) {
                if (appointment.getDay().equals(day)) this.doctor.cancelAppointment(appointment);
            }
        }
        this.day = day;
    }

    public void setTimeIn(String timeIn) {
        try{
            if (this.doctor.getAppointments().size() > 0 && this.day != null && this.day != day) {
                for (Appointment appointment : this.doctor.getAppointments()) {
                    if (appointment.getDay().equals(this.day) && appointment.getTimeStart() >= this.getTimeIn())
                        this.doctor.cancelAppointment(appointment);
                }
            }   
            this.timeIn = this.sdf.parse(timeIn);
        }catch(Exception e) {
            System.out.println("Time Date parse Exception " + e + " in setTimeIn => Availability.");
        }
    }

    public void setTimeOut(String timeOut) {
        try{
            if (this.doctor.getAppointments().size() > 0 && this.day != null && this.day != day) {
                for (Appointment appointment : this.doctor.getAppointments()) {
                    if (appointment.getDay().equals(this.day) && appointment.getTimeEnd() <= this.getTimeOut())
                        this.doctor.cancelAppointment(appointment);
                }
            }
            this.timeOut = this.sdf.parse(timeOut);
        }catch(Exception e) {
            System.out.println("Time Date parse Exception " + e + " in setTimeOut => Availability.");
        }
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public String getDay() {
        return this.day;
    }

    public long getTimeIn() {
        return this.timeIn.getTime();
    }

    public long getTimeOut() {
        return this.timeOut.getTime();
    }

}
