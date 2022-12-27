package com.gorest.crudtest;

import com.gorest.model.ProductPojo;
import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCrudTest extends TestBase {
    @Test
    public void verifyUserCreatedSuccessfully() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Ridhi");
        productPojo.setEmail("Ridhi@gmail.com");
        productPojo.setGender("female");
        productPojo.setStatus("active");

        Response response=given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .when()
                .body(productPojo)
                .post("/users");
        response.then().statusCode(201);
        response.prettyPrint();
    }
    @Test
    public void verifyUserUpdateSuccessfully(){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Ridhi");
        productPojo.setEmail("Ridhi12323@gmail.com");
        productPojo.setGender("female");
        productPojo.setStatus("active");

        Response response=given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .when()
                .body(productPojo)
                .patch("/users/11605");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void VerifyUserDeleteSuccessfully(){

        Response response=given()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .when()
                .delete("/users/11605");
        response.then().statusCode(204);
        response.prettyPrint();
    }
}
