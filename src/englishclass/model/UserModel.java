package englishclass.model;

import englishclass.conection.SQLCollum;
import englishclass.controller.Validator.StartDate;
import englishclass.controller.Validator.Password;

import java.util.Date;

public class UserModel {

    @SQLCollum(name="id")
    private Integer id;
    @SQLCollum(name = "nome")
    private String userName;
    @Password
    @SQLCollum(name = "password")
    private String password;
    private String fullName;
    @StartDate
    private Date startDate;
    private Aulas aulas;
    @SQLCollum(name = "user_image")
    private String userImage;
    @SQLCollum(name = "type")
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Aulas getAulas() {
        return aulas;
    }

    public void setAulas(Aulas aulas) {
        this.aulas = aulas;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
