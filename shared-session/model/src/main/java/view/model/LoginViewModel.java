package view.model;

/**
 * @author Michael
 * @create 10/30/2020 2:18 PM
 */
public class LoginViewModel {

    private String username;
    private String password;
    private String message;
    private String returnUrl;
    private boolean remberMe;


    public LoginViewModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
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

    public boolean isRemberMe() {
        return remberMe;
    }

    public void setRemberMe(boolean remberMe) {
        this.remberMe = remberMe;
    }
}



