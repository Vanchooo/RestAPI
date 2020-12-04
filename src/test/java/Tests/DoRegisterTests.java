package Tests;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import model.DoRegisterData;
import org.testng.annotations.*;



import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;


public class DoRegisterTests {
    String password = "1";
    DoRegisterData data;

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://users.bugred.ru/tasks/rest/")
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .build();

    @Test
    public void sendValidReq(){

        Faker faker = new Faker();
        data = new DoRegisterData(faker.name().firstName(),
                faker.bothify("???????###@gmail.com"), password);

        DoRegisterData responseData = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("doregister")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(DoRegisterData.class);

        assertEquals(data.email, responseData.email);
        assertEquals(data.name, responseData.name);

    }

    @Test
    public void sendSameUser(){

        Faker faker = new Faker();
        data = new DoRegisterData(faker.name().firstName(),
                faker.bothify("???????###@gmail.com"), password);

        DoRegisterData responseDataFirstReq = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("doregister")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(DoRegisterData.class);

        assertEquals(data.email, responseDataFirstReq.email);
        assertEquals(data.name, responseDataFirstReq.name);

        DoRegisterData responseDataSecondReq = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("doregister")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(DoRegisterData.class);

        assertEquals("error", responseDataSecondReq.type);

    }

    @Test
    public void sendWOMandatoryField(){

        Faker faker = new Faker();
        data = new DoRegisterData(faker.name().firstName(),
                faker.bothify("???????###@gmail.com"));

        DoRegisterData responseData = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("doregister")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(DoRegisterData.class);

        assertEquals("error", responseData.type);

    }

    @Test
    public void sendWOFields(){

        data = new DoRegisterData();

        DoRegisterData responseData = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("doregister")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(DoRegisterData.class);

        assertEquals("error", responseData.type);

    }

    @Test
    public void sendIncorrectEmail(){

        Faker faker = new Faker();
        data = new DoRegisterData(faker.name().firstName(),
                faker.bothify("???????###"));

        DoRegisterData responseData = given()
                .spec(requestSpec)
                .body(data)
                .when()
                .post("doregister")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(DoRegisterData.class);

        assertEquals("error", responseData.type);

    }
}
