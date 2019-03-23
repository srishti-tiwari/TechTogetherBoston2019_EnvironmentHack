package techtogether.io.myapplication;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    String name,location,gender,birthday;
    //List<String> interests;

    public User(String name, String location, String gender, String birthday) {
        this.name = name;
        this.location = location;
        this.gender = gender;
        this.birthday = birthday;
       // this.interests = interests;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
              //  ", interests=" + interests +
                '}';
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
}
