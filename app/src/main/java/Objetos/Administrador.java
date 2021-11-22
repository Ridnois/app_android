package Objetos;

public class Administrador {
    private int id;
    private String user;
    private String pass;

    public Administrador(){
        user = "Sebastian";
        pass = "1234";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Administrador(int id, String user, String pass) {
        this.id = id;
        this.user = user;
        this.pass = pass;
    }

    public boolean isAdmin(String user, String pass ) {
        if(this.user.equals(user) && this.pass.equals(pass)) {
            return true;
        }
        return false;
    }
}

