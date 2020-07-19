package oodj_assignment;
public class Educator extends User {
    private String modules;
    private String email;

    public Educator() {
    }

    public Educator(String modules, String email) {
        this.modules = modules;
        this.email = email;
    }

    public Educator(String modules, String email, String ID, int password, String name) {
        super(ID, password, name);
        this.modules = modules;
        this.email = email;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
