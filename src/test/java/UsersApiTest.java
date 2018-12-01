import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UsersApiTest {

    @Test
    public void getUsers() throws UnirestException {

        HttpResponse<JsonNode> response
                = Unirest.get("https://seleniumclass.000webhostapp.com/api/v1/users/1").asJson();

        System.out.println(response.getStatus());

        Assert.assertEquals(response.getStatus(), 200);

        Gson gson = new Gson();
        User myUser = gson.fromJson(response.getBody().toString(), User.class);

        System.out.println(myUser.name);
        System.out.println(myUser.username);
        System.out.println(myUser.phone);

        Assert.assertEquals(myUser.name, "SErgii");


    }


    @Test
    public void postUsers() throws UnirestException {

        User user1 = new User();

        user1.name = "sdafasdf";
        user1.username = "kjdfshak";
        user1.phone = "1233";
        user1.address = "123213";
        user1.email = "test@test.com";


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String jsonInString = gson.toJson(user1);


        HttpResponse<JsonNode> response
                = Unirest.post("https://seleniumclass.000webhostapp.com/api/v1/users")
                .body(jsonInString)
                .asJson();

        Assert.assertEquals(response.getStatus(), 201);
    }



}
