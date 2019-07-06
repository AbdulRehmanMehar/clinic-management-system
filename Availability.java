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
        doctor.addAvailability(this);
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTimeIn(String timeIn) {
        try{
            this.timeIn = this.sdf.parse(timeIn);
        }catch(Exception e) {
            System.out.println("Time Date parse Exception " + e + " in setTimeIn => Availability.");
        }
    }

    public void setTimeOut(String timeOut) {
        try{
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
