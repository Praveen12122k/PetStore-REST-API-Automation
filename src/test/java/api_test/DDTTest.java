package api_test;

import api_endpoints.UserEndpoints;
import api_payload.User;
import api_utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTTest {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void post_user(String userid, String username, String firstName, String lastName, String email, String password, String phone) {
        User payload = new User();
        payload.setId(Integer.parseInt(userid));
        payload.setUsername(username);
        payload.setFirstName(firstName);
        payload.setLastName(lastName);
        payload.setEmail(email);
        payload.setPassword(password);
        payload.setPhone(phone);
        payload.setUserStatus(0);

        Response post_response = UserEndpoints.create_user(payload);
        post_response.then().log().all();
        post_response.then().statusCode(200);
        Assert.assertEquals(post_response.statusCode(), 200);
    }
    @Test(priority = 2, dataProvider = "Username", dataProviderClass = DataProviders.class)
    public void test_deleteuser(String username){
        Response delete_user=UserEndpoints.delete_user(username);
        delete_user.then().statusCode(200);
    }
}
