package api_endpoints;

import api_payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserEndpoints {

    public static Response create_user(User Payload) {
        Response response = given()
//                .header("accept","application/json")
//                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(Payload)
                .when()
                .post(Routes.post_url);
        return response;
    }
    public static Response get_user(String Username) {
        Response response = given()
                .pathParams("username",Username)
                .when()
                .get(Routes.get_url);
        return response;
    }
    public static Response update_user(User Payload,String Username) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(Payload)
                .pathParams("username",Username)
                .when()
                .put(Routes.update_url);
        return response;
    }
    public static Response delete_user(String Username) {
        Response response = given()
                .pathParams("username",Username)
                .when()
                .delete(Routes.delete_url);
        return response;
    }
}


