package models;

public class Session {

    static final long serialVersionUID = 3L;
    private User user;
    private String sessionToken;

    public Session(User user, String sessionToken) {
        this.user = user;
        this.sessionToken = sessionToken;
    }


}
