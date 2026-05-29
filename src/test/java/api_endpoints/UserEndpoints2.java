package api_endpoints;

import api_payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static api_endpoints.Routes.post_url;
import static io.restassured.RestAssured.given;

public class UserEndpoints2 {

   public static ResourceBundle url(){
        ResourceBundle routes=ResourceBundle.getBundle("routes");//Load properties file
        return  routes;
    }

    public static Response create_user(User Payload) {
       String post_url=url().getString("post_url");
        Response response = given()
//                .header("accept","application/json")
//                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(Payload)
                .when()
                .post(post_url);
        return response;
    }
    public static Response get_user(String Username) {
        String get_url=url().getString("get_url");
        Response response = given()
                .pathParams("username",Username)
                .when()
                .get(get_url);
        return response;
    }
    public static Response update_user(User Payload,String Username) {
        String put_url=url().getString("update_url");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(Payload)
                .pathParams("username",Username)
                .when()
                .put(put_url);
        return response;
    }
    public static Response delete_user(String Username) {
        String delete_url=url().getString("delete_url");
        Response response = given()
                .pathParams("username",Username)
                .when()
                .delete(delete_url);
        return response;
    }
}


