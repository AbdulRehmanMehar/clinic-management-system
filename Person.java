import java.util.Arrays;
import java.util.ArrayList;
import java.security.MessageDigest;

public class Person {
    private String cnic;
    private String name;
    private Clinic clinic;
    private String username;
    private String password;
    private ArrayList<Role> roles;

    public Person(Clinic clinic, String cnic, String name, String username, String password) {
        this.setCnic(cnic);
        this.setName(name);
        this.setClinic(clinic);
        this.setUsername(username);
        this.setPassword(password);
        this.roles = new ArrayList<Role>();
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
        if (clinic != null) {
            clinic.addPerson(this);
        }
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String uname) {
        this.username = uname;
    }

    private boolean containsRoleOfType(String type) {
        for (Role role : this.roles) {
            if (role.getRole().matches(type)) return true;
        }
        return false;
    }
 
    public void addRole(Role role) {
        if(role != null && !this.roles.contains(role) && !this.containsRoleOfType(role.getRole())) {
            this.roles.add(role);
            role.setPerson(this);
        }
    }

    public void removeRole(Role role) {
        if (this.roles.remove(role)) {
            role.setPerson(null);
        }
    }

    public Clinic getClinic() {
        return this.clinic;
    }

    public String getUsername() {
        return this.username;
    }

    private String hashPassword(String password) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (Exception e) {
            System.out.println("Exception " + e + " Occured in Person.hashPassword(String):String");
        }
        return generatedPassword;
    }

    public void setPassword(String password) {
        
        this.password = this.hashPassword(password);
    }

    public boolean verifyPassword(String password) {
        return this.password.matches(hashPassword(password));
    }

    public String getCnic() {
        return this.cnic;
    }

    public String getName() {
        return this.name;
    }

    public Role[] getRoles() {
        return Arrays.copyOf(this.roles.toArray(), this.roles.size(), Role[].class);
    }

}
