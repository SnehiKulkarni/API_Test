package stepdefination;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBind;
import resources.util;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;

public class MyStepdefs extends util {

    RequestSpecification request_spec;
    ResponseSpecification response_spec;
    Response response;
    TestDataBind test = new TestDataBind();
    static String placeid ="";

    @Given("Add Place Payload with {string} {string}")
    public void add_Place_Payload_with(String name, String address) throws FileNotFoundException {
        //request spe
        request_spec = given().spec(requestSpecification()).body(test.addPlacePayLoad(name, address));
        //response spec
        response_spec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String methodname) {
        APIResources resourceAPI = APIResources.valueOf(resource);
        // Constructor call with value of resource pass from APIResource class
       if(methodname.equalsIgnoreCase("POST"))
       {
           response = request_spec.when().post(resourceAPI.getResource());
       } else if (methodname.equalsIgnoreCase("get"))
       {

           response = request_spec.when().get(resourceAPI.getResource());
       }

    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(response.getStatusCode(), 200);
    }

    @Override
    public RequestSpecification requestSpecification() throws FileNotFoundException {
        return super.requestSpecification();
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String value) {
        String responsedata = response.asString();
        JsonPath js = new JsonPath(responsedata);
        //   System.out.println("hi...."+js);
        assertEquals(util.getJson_Path(response,key), value);


    }

    @And("verify place_Id created maps to {string} using {string}")
    public void verifyPlace_IdCreatedMapsToUsing(String expectedname, String resourcename) throws FileNotFoundException {
        //ADDplace respose in which we pass json data (name,address etc) then we get response in form of place id.
         placeid= util.getJson_Path(response,"place_id");
        //pass query parameter
        request_spec=given().spec(requestSpecification()).queryParam("place_id",placeid);
        // Now we passing get request to featch stored data in json form using place id
        user_calls_with_http_request(resourcename,"GET");
           String name=util.getJson_Path(response,"name");
           assertEquals(expectedname,name);
    }



    @Given("DeletePlace Payload")
    public void deleteplacePayload() throws FileNotFoundException {
        request_spec =given().spec(requestSpecification()).body(test.deletePlacePayload(placeid));
    }
}



