package org.herokuapp.api;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.*;
import org.hamcrest.Matchers;
import org.herokuapp.helpers.Helpers;
import org.json.JSONArray;
import org.json.JSONObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;


public class ApiE2ETesting {

    private Helpers help = new Helpers();
    private List<String> postedAddIDs;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://admin-advertisement.herokuapp.com/";
    }


    @Test
    public void UC01_postNewAdvertisement() throws Exception {
        String requestBody = help.readResourceFileAsString("UC01_postNewAdvertisement/RequestBody.json", ApiE2ETesting.class);
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .log().all()
                        .when()
                        .post("/api/advertisements")
                        .then()
                        .assertThat()
                        .body("_id", Matchers.notNullValue())
                        .statusCode(200)
                        .log().all()
                        .extract().response();

        JSONArray responseBody = new JSONArray(response.body().asString());
        postedAddIDs = new LinkedList<>();
        for (int i = 0; i < responseBody.length(); i++) {
            JSONObject jsonObj = responseBody.getJSONObject(i);
            postedAddIDs.add(jsonObj.get("_id").toString());
        }

    }

    @Test
    public void UC02_validatePostedNewAdvertisement() throws Exception {
        String requestBody = help.readResourceFileAsString("UC01_postNewAdvertisement/RequestBody.json", ApiE2ETesting.class);
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .log().all()
                        .when()
                        .get("/api/advertisements/" + postedAddIDs.get(0))
                        .then()
                        .assertThat()
                        .body("name",Matchers.equalTo("xa"))
                        .statusCode(200)
                        .log().all()
                        .extract().response();

    }



    @Test
    public void UC03_getAllAdvertisement() throws Exception {
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get("/api/advertisements")
                        .then()
                        .assertThat()
                        .body(Matchers.notNullValue())
                        .statusCode(200)
                        .log().all()
                        .extract().response();

    }

    @Test
    public void UC04_getOnlyOneAdd() {
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get("/api/advertisements/" + postedAddIDs.get(0))
                        .then()
                        .assertThat()
                        .body(Matchers.notNullValue())
                        .statusCode(200)
                        .log().all()
                        .extract().response();

    }

    @Test
    public void UC05_updateAdvertisement() throws Exception {
        String requestBody = help.readResourceFileAsString("UC05_putAdvertisements/RequestBody.json", ApiE2ETesting.class);

        given()
                .contentType(ContentType.JSON)
                .body(requestBody.replaceAll("\\{Id.value}", postedAddIDs.get(0)))
                .log().all()
                .when()
                .put("/api/advertisements/" + postedAddIDs.get(0))
                .then()
                .assertThat()
                .statusCode(200)
                .log().all()
                .extract().response();
    }

    @Test
    public void UC06_validateUpdatedAdvertisement() throws Exception {

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("/api/advertisements/" + postedAddIDs.get(0))
                .then()
                .assertThat()
                .body("name",Matchers.equalTo("Khaled Mc"))
                .statusCode(200)
                .log().all()
                .extract().response();
    }


}
