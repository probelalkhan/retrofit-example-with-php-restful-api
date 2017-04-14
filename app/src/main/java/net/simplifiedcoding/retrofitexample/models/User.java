package net.simplifiedcoding.retrofitexample.models;

/**
 * Created by Belal on 14/04/17.
 */

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String gender;

    public User(String name, String email, String password, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public User(int id, String name, String email, String gender){
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public User(int id, String name, String email, String password, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getGender() {
        return gender;
    }
}
