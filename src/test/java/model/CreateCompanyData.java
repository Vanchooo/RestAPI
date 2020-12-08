package model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCompanyData {
    public String companyName;
    public String companyType;
    public ArrayList<String> companyUsers;
    public String emailOwner;
    public String type;
    public String id_company;

    public CreateCompanyData(){};

    public CreateCompanyData(String companyName, String companyType, ArrayList<String> companyUsers) {
        this.companyName = companyName;
        this.companyType = companyType;
        this.companyUsers = companyUsers;
    }


    public CreateCompanyData(String companyName, String companyType, ArrayList<String> companyUsers, String emailOwner) {
        this.companyName = companyName;
        this.companyType = companyType;
        this.companyUsers = companyUsers;
        this.emailOwner = emailOwner;
    }

    @JsonGetter("company_name")
    public String getCompanyName() {
        return companyName;
    }

    @JsonGetter("company_type")
    public String getCompanyType() {
        return companyType;
    }

    @JsonGetter("company_users")
    public ArrayList<String> getCompanyUsers() {
        return companyUsers;
    }

    @JsonGetter("email_owner")
    public String getEmailOwner() {
        return emailOwner;
    }

    @JsonGetter("type")
    public String getType() {
        return type;
    }

    @JsonGetter("id_company")
    public String getId_company() {
        return id_company;
    }
}
