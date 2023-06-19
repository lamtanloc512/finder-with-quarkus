package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.exception.GeneralException;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello from RESTEasy Reactive"));
    }

    @Test
    public void testFileUpload_Null() throws IOException {
//        given().when().post("/finder/upload")
//                .then()
//                .statusCode(500)
//                .body(is(null));

        var restResponse = RestResponse.ok(null);
        System.out.println(restResponse);
    }

}