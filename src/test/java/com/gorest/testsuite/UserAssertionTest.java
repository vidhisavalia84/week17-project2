package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.hasItem;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        //RestAssured.port= ;
        //  RestAssured.basePath="/sers?page=1&per_page=20";

        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("total.size()", equalTo(20));
    }

    // 2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    //  2. Verify the if the name of id = 5336 is equal to ”Amogh Nayar”
    @Test
    public void test002() {
        response.body("findAll{it.id == 5336}.name", hasItem("Amogh Nayar"));
    }

    //  3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test003() {
        response.body("name", hasItem("Amogh Nayar"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Balgopal Verma, Jagmeet Kaniyar,Amish Mehrotra I)
    @Test
    public void test004() {
        response.body("name", hasItems("Balgopal Verma", "Jagmeet Kaniyar", "Amish Mehrotra I"));
    }

    //5. Verify the emai of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test005() {
        response.body("findAll{it.id == 5341}.email", hasItems("kaniyar_jagmeet@friesen-wisozk.co"));
    }

    // 6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test006() {
        response.body("findAll{it.name == 'Mr. Kamalesh Jain'}.status", hasItem("inactive"));
    }

    //  7. Verify the Gender = male of user name is “Vaijayanti Kapoor PhD”
    @Test
    public void test007() {
        response.body("findAll{it.name == 'Vaijayanti Kapoor PhD'}.gender", hasItem("male"));
    }
}
