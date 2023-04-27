package ma.fstt;

public class Login {
    private Long id;
    private String username;
    private String password;
    private Boolean userType;
    private String emailAddress;


    public Login(Long id, String username, String password, Boolean userType, String emailAddress) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.emailAddress = emailAddress;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getUserType() {
        return userType;
    }

    public void setUserType(Boolean userType) {
        this.userType = userType;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "login{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
