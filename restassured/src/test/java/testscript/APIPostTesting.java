package testscript;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import reusable.*;

public class APIPostTesting {
	
	public static void main(String args[]) throws Exception {
		
		for (int k=10;k<50;k=k+10) {
		PostcallNumbertoWords(k);
		}
	}
	
	public static void PostcallNumbertoWords(int num){
		
		Response res = given()
							.contentType(ContentType.XML) //the body is XML 
							.header("Content-Type", "text/xml; charset=utf-8")
							.accept(ContentType.XML) //response need to be XML
							.body("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
									+ "  <soap:Body>\r\n"
									+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
									+ "      <ubiNum>"+num+"</ubiNum>\r\n"
									+ "    </NumberToWords>\r\n"
									+ "  </soap:Body>\r\n"
									+ "</soap:Envelope>")
							.when()
							.post(Reusable.readpropertyfile("NumbertoWords"));
		
		System.out.println(res.statusCode());		
		System.out.println(res.body().asString());
		
	}

}
