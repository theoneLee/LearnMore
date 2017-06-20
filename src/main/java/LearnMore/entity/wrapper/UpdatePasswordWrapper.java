package LearnMore.entity.wrapper;

/**
 * Created by Lee on 2017/6/20 0020.
 */
public class UpdatePasswordWrapper {
    private String username;
    private String password;
    private String newPassword;
    private String newPasswordChecked;

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordChecked() {
        return newPasswordChecked;
    }

    public void setNewPasswordChecked(String newPasswordChecked) {
        this.newPasswordChecked = newPasswordChecked;
    }
}
