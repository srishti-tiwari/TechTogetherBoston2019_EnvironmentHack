package techtogether.io.myapplication;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    String name;
    String location;
    String gender;
    String birthday;
    String email;
    List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public User(String name, String location, String gender, String birthday, List<Category> categoryList, String email) {
        this.name = name;
        this.location = location;
        this.gender = gender;
        this.birthday = birthday;
        this.categoryList = categoryList;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", categoryList=" + categoryList +
                '}';
    }


}
