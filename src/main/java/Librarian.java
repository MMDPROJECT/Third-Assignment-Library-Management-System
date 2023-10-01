public class Librarian {

    private String username;
    private String password;

    public Librarian(String username, String password){

        this.username = username;
        this.password = password;
    }

    //getter methods

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //setter methods

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}