import com.apttus.api.RadioStations;
import com.apttus.api.RadioStationsRetrieveResponse;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RadioStationAPITestSpec {

  public static final String APP_URI = "http://api.openweathermap.org/data/3.0/stations";

  public static final String Radio_Data_1 = "{\n" +
    "   \"external_id\": \"DEMO_TEST001\",\n" +
    "   \"name\": \"Interview Station 102\",\n" +
    "   \"latitude\": 33.33,\n" +
    "   \"longitude\": -111.43,\n" +
    "   \"altitude\": 444\n" +
    "}";

  public static final String Radio_Data_2 = "{\n" +
    "   \"external_id\": \"Interview1 \",\n" +
    "   \"name\": \"Interview Station 100\",\n" +
    "   \"latitude\": 33.44,\n" +
    "   \"longitude\": -12.44,\n" +
    "   \"altitude\": 444\n" +
    "}";

  public static String API_Key = "900bc225e6c00f9163f2cceeff9c06b6";

  public static String first_id = "";

  public static String second_id = "";

  public static String error_message_WO_API_Key = "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.";

  public static String delete_error = "Station not found";

  @Test
  public void registerStationsWithInvalidApiKey() {

    //1. Validate that attempt to register a weather station without an API key
    String invalidAPIKey = given().log().all().
      body(Radio_Data_1).
      when().
      post(APP_URI).
      then().
      assertThat().log().all().
      statusCode(401).extract().response().asString();

    JsonPath jsonPath = new JsonPath(invalidAPIKey);
    String message = jsonPath.getString("message");

    Assert.assertEquals(message, error_message_WO_API_Key, "The response message is invalid");

  }

  @Test(priority = 1)
  public void registerStationsWithValidApiKeyAndValidateGetResponse() {

    //1. Validate that attempt to register a weather station without an API key
    Response addFirstStation = given().log().all().
      queryParam("APPID", API_Key).
      contentType(ContentType.JSON).
      body(Radio_Data_1).
      when().
      post(APP_URI).
      then().
      assertThat().log().all().
      statusCode(201).extract().response();

    JsonPath js1 = new JsonPath(addFirstStation.asString());
    first_id = js1.getString("ID");

    RadioStations firstRadioStationAdd = addFirstStation.body().as(RadioStations.class);

    RadioStationsRetrieveResponse firstRadioStationAddResponse = RadioStations.convertToRadioResponse(firstRadioStationAdd);

    Response addSecondStation = given().log().all().
      queryParam("APPID", API_Key).
      contentType(ContentType.JSON).
      body(Radio_Data_2).
      when().
      post(APP_URI).
      then().
      assertThat().log().all().
      statusCode(201).extract().response();

    JsonPath js2 = new JsonPath(addSecondStation.asString());
    second_id = js2.getString("ID");

    RadioStations secondRadioStationAdd = addSecondStation.body().as(RadioStations.class);

    RadioStationsRetrieveResponse secondRadioStationAddResponse = RadioStations.convertToRadioResponse(secondRadioStationAdd);

    Response getStations = given().log().all().
      queryParam("APPID", API_Key).
      contentType(ContentType.JSON).
      when().
      get(APP_URI).
      then().
      assertThat().log().all().
      statusCode(200).extract().response();

    RadioStationsRetrieveResponse[] allAvailableStations = getStations.body().as(RadioStationsRetrieveResponse[].class);

    Assert.assertEquals(allAvailableStations[0], firstRadioStationAddResponse);
    Assert.assertEquals(allAvailableStations[1], secondRadioStationAddResponse);
  }

  @Test(priority = 2)
  public void registeredStationsDelete() {
    given().log().all()
      .contentType(ContentType.JSON)
      .queryParam("APPID", API_Key)
      .delete(APP_URI + "/" + first_id)
      .then()
      .assertThat().log().all()
      .statusCode(204);

    given().log().all()
      .contentType(ContentType.JSON)
      .queryParam("APPID", API_Key)
      .delete(APP_URI + "/" + second_id)
      .then()
      .assertThat().log().all()
      .statusCode(204);

    String invalidDelete = given().log().all()
      .contentType(ContentType.JSON)
      .queryParam("APPID", API_Key)
      .delete(APP_URI + "/" + second_id)
      .then()
      .assertThat().log().all()
      .statusCode(404).extract().response().asString();

    JsonPath jsonPath = new JsonPath(invalidDelete);
    String message = jsonPath.getString("message");

    Assert.assertEquals(message, delete_error, "The response message is invalid");
  }

}