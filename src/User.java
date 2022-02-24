public class User {

    int id;
    String username;
    String password;
    int customerId;

    public User(int id, String username, String password, int customerId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.customerId = customerId;
    }
    public String getUsername() {
        return username;
    }
    public int getCustomerId(){
        return id;
    }
}