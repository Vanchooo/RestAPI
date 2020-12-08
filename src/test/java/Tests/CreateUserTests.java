package Tests;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.CreateCompanyData;
import model.CreateTaskData;
import model.CreateUserData;
import model.DoRegisterData;
import org.testng.annotations.*;


import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class CreateUserTests {

    String idCompany;
    String idTask;
    CreateUserData data;
    ArrayList<String> tasks = new ArrayList<String>();
    ArrayList<String> companies = new ArrayList<String>();

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://users.bugred.ru/tasks/rest/")
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .build();

    @BeforeTest
    public void setUp(){
        CreateCompanyData dataCompany;
        CreateTaskData dataTask;
        ArrayList<String> users = new ArrayList<String>();
        String emailOwner = "manager@mail.ru";

        String companyType = "ООО";
        users.add("manager@mail.ru");

        Faker faker = new Faker();
        dataCompany = new CreateCompanyData(faker.company().name(), companyType, users, emailOwner);
        dataTask = new CreateTaskData
                (faker.bothify("?????###"), faker.bothify("?????###"), "manager@mail.ru", "manager@mail.ru");

        CreateCompanyData responseDataCompany = given()
                .spec(requestSpec)
                .body(dataCompany)
                .when()
                .post("createcompany")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(CreateCompanyData.class);

        assertEquals("success", responseDataCompany.type);

        idCompany = responseDataCompany.id_company;

        CreateTaskData responseDataTask = given()
                .spec(requestSpec)
                .body(dataTask)
                .when()
                .post("createtask")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(CreateTaskData.class);

        assertEquals("success", responseDataTask.type);

        idTask = responseDataTask.idTask;

    }

    @Test
    public void sendValidReq(){

        tasks.add(idTask);
        companies.add(idCompany);

        Faker faker = new Faker();
        data = new CreateUserData(faker.bothify("???????###@gmail.com"), faker.name().firstName(), tasks, companies);

        Response responseData = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("createuser")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        assertEquals(responseData.path("email"), data.email);
        assertEquals(responseData.path("name"), data.name);

    }

    @Test
    public void sendSameUser(){

        tasks.add(idTask);
        companies.add(idCompany);

        Faker faker = new Faker();
        data = new CreateUserData(faker.bothify("???????###@gmail.com"), faker.name().firstName(), tasks, companies);

        Response responseData = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("createuser")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        assertEquals(responseData.path("email"), data.email);
        assertEquals(responseData.path("name"), data.name);

        Response responseDataSecondReq = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("createuser")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        assertEquals(responseDataSecondReq.path("type"), "error");

    }

    @Test
    public void sendWOMandatoryField(){

        tasks.add(idTask);
        companies.add(idCompany);

        Faker faker = new Faker();
        data = new CreateUserData(faker.bothify("???????###@gmail.com"), faker.name().firstName(), tasks);

        CreateUserData responseData = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("createuser")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(CreateUserData.class);

        assertEquals(responseData.type, "error");

    }

    @Test
    public void sendWOFields(){

        data = new CreateUserData();

        CreateUserData responseData = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("createuser")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(CreateUserData.class);

        assertEquals("error", responseData.type);

    }

    @Test
    public void sendIncorrectEmail(){

        tasks.add(idTask);
        companies.add(idCompany);

        Faker faker = new Faker();
        data = new CreateUserData(faker.bothify("???????###"), faker.name().firstName(), tasks, companies);

        CreateUserData responseData = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("createuser")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(CreateUserData.class);

        assertEquals(responseData.type, "error");

    }

}
