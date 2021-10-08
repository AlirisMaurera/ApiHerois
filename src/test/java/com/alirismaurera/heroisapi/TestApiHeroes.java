package com.alirismaurera.heroisapi;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static io.restassured.RestAssured.given;

public class TestApiHeroes {

    String url = "http://localhost:8080/heroes";

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test
    public void listarHeroesTest(){

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(url)
        .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void listarUmHeroTest(){

        String id = "7";

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(url + "/" + id)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("homem-formiga"))
                .body("universe", is("marvel"))
                .body("films", is(4));
    }

    @Test
    public void cadastrarUmHeroTest() throws IOException {

        String jsonBody = lerJson("JsonTest/hero1.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(url)
        .then()
                .statusCode(201)
                .body("name", is("Batman"))
                .body("universe", is("comics"))
                .body("films", is(1));
    }

    @Test
    public void deletarUmHeroTest(){

        String id = "1";

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .delete(url + "/" + id)
        .then()
                .statusCode(204);
    }

    @Test
    public void atualizarUmHero() throws IOException {

        String id = "20";
        String jsonBody = lerJson("JsonTest/hero1.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .put(url + "/" + id)
        .then()
                .statusCode(200)
                .body("name", is("Batman"))
                .body("universe", is("comics"))
                .body("films", is(7));
    }

}
