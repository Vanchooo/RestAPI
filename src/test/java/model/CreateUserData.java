package model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.HashSet;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserData {
    public String email;
    public String name;
    public ArrayList<String> tasks;
    public ArrayList<String> companies;
    public String type;

    public CreateUserData(){
    }

    public CreateUserData(String email, String name, ArrayList<String> tasks, ArrayList<String> companies) {
        this.email = email;
        this.name = name;
        this.tasks = tasks;
        this.companies = companies;
    }

    public CreateUserData(String email, String name, ArrayList<String> tasks) {
        this.email = email;
        this.name = name;
        this.tasks = tasks;
    }

    @JsonGetter("email")
    public String getEmail() {
        return email;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonGetter("type")
    public String getType() {
        return type;
    }

//    //@JsonGetter("tasks")
//    public ArrayList<String> getTasks() {
//        return tasks;
//    }
//
//    //@JsonGetter("companies")
//    public ArrayList<String> getCompanies() {
//        return companies;
//    }
}
