package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class StoresCRUDTest extends TestBase {
    static String name = "PrimUser" + TestUtils.getRandomValue();
    static String type = "BigBox" + TestUtils.getRandomValue();
    static String address = TestUtils.getRandomValue() +"Random Street" ;
    static String address2 = "Roaming Street";
    static String city =  "London" ;
    static String state = "England";
    static String zip = "234567";
    static double lat = 45.65874;
    static double lng = -95.56235;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId ;

    @Test
    public void test001() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post("/stores");
        storeId =response.then().contentType(ContentType.JSON).extract().path("id");
        response.then().statusCode(201);
        System.out.println("Id No is" +storeId);
        response.prettyPrint();

    }

    @Test
    public void test002() {
        Response response = given()
                .when()
                .get("/stores");

        response.then().statusCode(200);
        response.prettyPrint();


    }
    @Test
    public void test003() {
        Response response = given()
                .when()
                .get("/stores" +"/" + storeId);
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void test004() {
        StorePojo storePojo = new StorePojo();
        storePojo.setHours("Mon: 10-10; Tue: 10-8; Wed: 10-7; Thurs: 10-6; Fri: 10-5; Sat: 10-4; Sun: 10-3");
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .patch("/stores" +"/" + storeId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

    @Test
    public void test005() {
     Response response = given()
             .when()
             .delete("/stores" +"/" + storeId);
     response.prettyPrint();
     response.then().log().all().statusCode(200);


    }
}
