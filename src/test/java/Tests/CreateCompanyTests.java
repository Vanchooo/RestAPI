package Tests;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import model.CreateCompanyData;
import model.DoRegisterData;
import org.testng.annotations.*;


import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class CreateCompanyTests {

    CreateCompanyData data;
    ArrayList<String> users = new ArrayList<String>();
    String emailOwner = "manager@mail.ru";

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://users.bugred.ru/tasks/rest/")
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .build();

    @Test
    public void sendValidReq(){

        String companyType = "ООО";
        users.add("manager@mail.ru");

        Faker faker = new Faker();
        data = new CreateCompanyData(faker.company().name(), companyType, users, emailOwner);

        CreateCompanyData responseData = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("createcompany")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(CreateCompanyData.class);

        assertEquals("success", responseData.type);

    }

    @Test
    public void sendCompanyWithSameName(){

        String companyType = "ООО";
        users.add("manager@mail.ru");

        Faker faker = new Faker();
        data = new CreateCompanyData(faker.company().name(), companyType, users, emailOwner);

        CreateCompanyData responseDataFirstReq = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("createcompany")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(CreateCompanyData.class);


        assertEquals(responseDataFirstReq.type,"success");

        CreateCompanyData responseDataSecondReq = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("createcompany")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(CreateCompanyData.class);

        assertEquals(responseDataSecondReq.type, "success");

    }

    @Test
    public void sendWOMandatoryField(){

        String companyType = "ООО";

        Faker faker = new Faker();
        data = new CreateCompanyData(faker.company().name(), companyType, users);

        CreateCompanyData responseData = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("createcompany")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(CreateCompanyData.class);

        assertEquals("error", responseData.type);

    }

    @Test
    public void sendWOFields(){


        data = new CreateCompanyData();

        CreateCompanyData responseData = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("createcompany")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(CreateCompanyData.class);

        assertEquals("error", responseData.type);

    }

    @Test
    public void sendIncorrectCompanyType(){

        String companyType = "ZZZ";
        users.add("manager@mail.ru");

        Faker faker = new Faker();
        data = new CreateCompanyData(faker.company().name(), companyType, users, emailOwner);

        CreateCompanyData responseData = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("createcompany")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(CreateCompanyData.class);

        assertEquals("error", responseData.type);

    }
}
