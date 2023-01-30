package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

public class util {
    public static RequestSpecification req;

    public RequestSpecification requestSpecification() throws FileNotFoundException {

        if (req == null) {
            PrintStream stream = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
                    addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(stream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(stream))
                    .setContentType(ContentType.JSON).build();

            return req;
        }
        return req;

    }

    public static String getJson_Path(Response res, String key) {
        String json_response = res.asString();
        JsonPath js = new JsonPath(json_response);
        String value =js.get(key).toString();
        return value;


    }
}