package api_test;

import api_endpoints.UserEndpoints;
import api_payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {
    Faker dummy;
    User payload;
    public Logger log;

    @BeforeClass
    public  void setup(){
      dummy= new Faker();
      payload=new User();
      log= LogManager.getLogger(this.getClass());

      payload.setId(dummy.idNumber().hashCode());
      payload.setUsername(dummy.name().username());
      payload.setFirstName(dummy.name().firstName());
      payload.setLastName(dummy.name().lastName());
      payload.setEmail(dummy.internet().emailAddress());
      payload.setPassword(dummy.internet().password());
      payload.setPhone(dummy.phoneNumber().cellPhone());
      payload.setUserStatus(0);
    }

    @Test(priority=1)
    public  void test_postuser(){
       log.info("Start of Create User API");
       Response post_response= UserEndpoints.create_user(payload);
       post_response.then().log().all();
       post_response.then().statusCode(200);
       Assert.assertEquals(post_response.statusCode(),200);
       log.info("End of Create User API");
    }
    @Test(priority = 2)
    public void test_getuser(){
        Response get_response=UserEndpoints.get_user(this.payload.getUsername());
        log.info("User Details is fetched");
        get_response.then().log().all();
        get_response.then().statusCode(200);
        log.info("get API is Successfully returned Users");

    }
    @Test(priority = 3)
    public  void test_updateuser(){
        payload.setFirstName(dummy.name().firstName());
        payload.setLastName(dummy.name().lastName());
        payload.setEmail(dummy.internet().emailAddress());
        log.info("Payload is crated for update user");
        Response put_response=UserEndpoints.update_user(payload,this.payload.getUsername());
        put_response.then().log().all();
        put_response.then().statusCode(200);
        log.info("User is updated");
    }
    @Test(priority = 4)
    public void test_deleteuser(){
        Response delete_user=UserEndpoints.delete_user(this.payload.getUsername());
        log.info("User is Deleted");
    }
}
