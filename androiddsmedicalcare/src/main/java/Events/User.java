package Events;

/**
 * Created by adrianlim on 15-06-16.
 */
public class User {
    private String username;
    private String password;
    private String email;
    private String _id;

    public User(String username, String password, String email, String _id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String get_id() {
        return _id;
    }
}
