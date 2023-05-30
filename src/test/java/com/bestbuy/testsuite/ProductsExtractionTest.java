package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;    // if you have port then only use this line
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);

    }

    // 21. Extract the limit
    @Test
    public void test021() {
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is : " + limit);
    }


    //22. Extract the total
    @Test
    public void test022() {
        int total = response.extract().path("total");
        System.out.println("The value of total is : " + total);
    }


    //23. Extract the name of 5th product
    @Test
    public void test023() {
        String productName = response.extract().path("data[4].name");
        System.out.println("The name of fifth product is: " + productName);
    }

    //24. Extract the names of all the products
    @Test
    public void test024() {
        List<String> listOfProducts = response.extract().path("data.name");
        System.out.println("List of name of all products are: " + listOfProducts);
    }

    //25. Extract the productId of all the products
    @Test
    public void test025() {
        List<Integer> listOfProductId = response.extract().path("data.id");
        System.out.println("List of product Id of all products are: " + listOfProductId);
    }

    //26. Print the size of the data list
    @Test
    public void test026() {
        List<Integer> ids = response.extract().path("data");
        int num = ids.size();
        System.out.println("The size of the data list : " + num);
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test027() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}"); // response is returning list of map, but inside map we use ? or object
        System.out.println("The values for product name 'Energizer - MAX Batteries AA (4-Pack)' are: " + values);
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test028() {
        List<Double> model = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("The price of product name 'Energizer - N Cell E90 Batteries (2-Pack)' is : " + model);
    }

    //29. Get all the categories of 8th products
    @Test
    public void test029() {
        List<HashMap<String, ?>> categoriesList = response.extract().path("data[7].categories");
        System.out.println("All the categories of 8th products" + categoriesList);
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test030() {
        List<HashMap<String, ?>> category = response.extract().path("data.findAll{it.id == 150115}.categories");
        System.out.println("The name categories of the store whose product id is: " + category);
    }

    //31. Get all the descriptions of all the products
    @Test
    public void test031() {
        List<String> listOfDescriptions = response.extract().path("data.description");
        System.out.println("The description of all products are: " + listOfDescriptions);
    }

    //32. Get id of all the all categories of all the products
    @Test
    public void test032() {
        List<HashMap<String, ?>> listOfAllCategories = response.extract().path("data.categories.id");
        System.out.println("Id of all categories of all products are: " + listOfAllCategories);

    }

    //33. Find the product names Where type = HardGood
    @Test
    public void test033() {
        List<String> productName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println(productName);
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test034() {
        //List<HashMap<String, ?>> totalCategories = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        List<HashMap<String, ?>> totalCategories1 = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println(totalCategories1);
        int size = totalCategories1.size();
        System.out.println(size);


    }

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test035() {
        List<String> createdAtList = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("CreatedAt (price < 5.49): " + createdAtList);


    }

    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test036() {
        List<HashMap<String, ?>> nameOfCategories = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("Names of all categories are: " + nameOfCategories);
    }

    //37. Find the manufacturer of all the products
    @Test
    public void test037() {
        List<String> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("List of manufacturer of all products are: " + manufacturer);
    }

    //38. Find the imge of products whose manufacturer is =Energizer
    @Test
    public void test038() {
        List<String> imageOfProducts = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("Image of products :" + imageOfProducts);
    }

    // 39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test039() {
        List<String> createdAt = response.extract().path("data.findAll{it.price>5.99}.categories.createdAt");
        System.out.println("CreatedAt (price > 5.99): " + createdAt);


    }

    // 40. Find the uri of all the products
    @Test
    public void test040() {
        List<String> url = response.extract().path("data.url");
        System.out.println("URL of all products are: " + url);
    }


}
