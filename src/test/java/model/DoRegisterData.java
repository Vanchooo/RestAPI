package model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DoRegisterData {
    public String name;
    public String email;
    public String password;
    public String type;

    public DoRegisterData(){

    }

    public DoRegisterData(String name, String email){
        this.name = name;
        this.email = email;
    }

    public DoRegisterData(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public DoRegisterData(String name, String email, String password, String type){
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    @JsonGetter("name")
    public String getName(){
        return name;
    }

    @JsonGetter("email")
    public String getEmail(){
        return email;
    }

    @JsonGetter("password")
    public String getPassword() {
        return password;
    }

    @JsonGetter("type")
    public String getType() {
        return type;
    }

}
