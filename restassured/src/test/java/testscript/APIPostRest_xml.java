package testscript;

import static io.restassured.RestAssured.given;

import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import reusable.Reusable;
import static org.hamcrest.CoreMatchers.equalTo;
import io.restassured.path.xml.XmlPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.path.xml.config.XmlPathConfig.xmlPathConfig;



public class APIPostRest_xml {

	public static long theRandomNum = (long) (Math.random()*Math.pow(10,10));
	
	public static String testEmail = null;
	public static long mobileNumber = 0;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

//		PostcallapiUsers();	
//		PostcallapiUsersPassEmailparameter();
//		ComparePostmethodresults();
		
	}
		
public static void PostcallapiUsers(){
		
		Response res = given()
							.contentType(ContentType.JSON) //the body is XML 
							.accept(ContentType.JSON) //response need to be XML
							.body("{\r\n"
									+ "    \"name\": \"morpheus\",\r\n"
									+ "    \"job\": \"leader\"\r\n"
									+ "}")
							.when()
							.post(Reusable.readpropertyfile("PostRESTcall"));
		
		System.out.println(res.statusCode());		
		System.out.println(res.body().asString());
		
	}


	public static void PostcallapiUsersPassEmailparameter(){
	
	
	Response res = given()
						.contentType(ContentType.JSON) //the body is XML 
						.accept(ContentType.JSON) //response need to be XML
						.body("{\r\n"
								+ "    \"name\": \"morpheus\",\r\n"
								+ "    \"email\": \""+getSaltString()+"\",\r\n"
								+ "    \"Mobile\": \""+theRandomNum+"\",\r\n"
								+ "    \"job\": \"leader\"\r\n"
								+ "}")
						.when()
						.post(Reusable.readpropertyfile("PostRESTcall"));
	
	System.out.println(res.statusCode());		
	System.out.println(res.body().asString());
	
	}
	
	public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString()+"@gmail.com";
        return saltStr;

    }
	
	public static void ComparePostmethodresults() {
		
		testEmail = getSaltString();
		mobileNumber = theRandomNum;
		
		given()
				.contentType(ContentType.JSON)
				.accept(ContentType.ANY)
				.body("{\r\n"
						+ "    \"name\": \"morpheus\",\r\n"
						+ "    \"email\": \""+testEmail+"\",\r\n"
						+ "    \"Mobile\": \""+mobileNumber+"\",\r\n"
						+ "    \"job\": \"leader\"\r\n"
						+ "}")
				.when()
				.post(Reusable.readpropertyfile("PostRESTcall"))
				.then().assertThat()
				.body("email", equalTo(testEmail))
				.body("Mobile", equalTo(String.valueOf(mobileNumber)));
				
	}

	
	
	
	@Test
	public static void ComparePostmethodresults_TestNG() {
		
		testEmail = getSaltString();
		mobileNumber = theRandomNum;
		
		given()
				.contentType(ContentType.JSON)
				.accept(ContentType.ANY)
				.body("{\r\n"
						+ "    \"name\": \"morpheus\",\r\n"
						+ "    \"email\": \""+testEmail+"\",\r\n"
						+ "    \"Mobile\": \""+mobileNumber+"\",\r\n"
						+ "    \"job\": \"leader\"\r\n"
						+ "}")
				.when()
				.post(Reusable.readpropertyfile("PostRESTcall"))
				.then().assertThat()
				.body("email", equalTo(testEmail))
				.body("Mobile", equalTo(String.valueOf(mobileNumber)));
				
	}
	
	
	
	@Test
	public static void soapGet_TestNG() {
		
		given()
				.contentType(ContentType.ANY)
				.accept(ContentType.ANY)
				.when()
				.get(Reusable.readpropertyfile("SOAPXMLMultipleHirarchy"))
				.then().assertThat()
				.body("bookstore.book[0].title", equalTo("The Nightingale"))
				.body("bookstore.book[0].price.paperback", equalTo("200"))
				.body("bookstore.book[1].title", equalTo("HarryPotter"));
				
	}
	
@Test	
public static void soapGetBIGXML_TestNG() {
		
	given()
				.contentType(ContentType.ANY)
				.accept(ContentType.ANY)
				.when()
				.get(Reusable.readpropertyfile("SOAPBIGXMLMultiple"))
				.then().assertThat()
				.body("definitions.message[0].part[1].@name", equalTo("RequestName"))
				.body("definitions.message[0].part[1].@type", equalTo("xsd:string"))

				//				.body("definitions.types[0].@xmlns", equalTo("http://www.w3.org/2001/XMLSchema"));
				.body("definitions.@name", equalTo("SQLDataSoap"));

//	XmlPath xmlPath = new XmlPath(response.body().asString()).using(xmlPathConfig().namespaceAware(false));
//		assertThat(xmlPath.getString("definitions.@'xmlns:xsd'"), equalTo("http://www.w3.org/2001/XMLSchema"));

				
	}	
	
///bookstore/book[1]/title <-- This is way to pass XML tags by using XPATH


@Test
public static void xmlcolonvalueread() {
	
	Response response =
            given()
                    .contentType(ContentType.XML)
                    .accept(ContentType.XML)
                    .when()
                    .get("http://soapclient.com/xml/SQLDataSoap.WSDL");		
	XmlPath xmlPath = new XmlPath(response.body().asString()).using(xmlPathConfig().namespaceAware(false));
    assertThat(xmlPath.getString("definitions.message[0].part[1].@name"), equalTo("RequestName"));
	assertThat(xmlPath.getString("definitions.@'xmlns:xsd'"), equalTo("http://www.w3.org/2001/XMLSchema"));
}
}