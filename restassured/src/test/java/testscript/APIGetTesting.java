package testscript;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import reusable.*;

public class APIGetTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//googleGetCall();
		infyniGetCall();
		
	}

	public static void googleGetCall ()
	{
		
		RestAssured.baseURI = Reusable.readpropertyfile("TestURL") ;
		
		Response res = RestAssured.get();
		
		int responsecode = res.getStatusCode();
		
		System.out.println(responsecode);
		
		System.out.println(res.body().asString());
		
	}
	
	public static void infyniGetCall()
	{
		
		RestAssured.baseURI = Reusable.readpropertyfile("InfyniURL");
		
		Response InfRes = RestAssured.get();
		
		int Infresponsecode = InfRes.getStatusCode();
		
		System.out.println(Infresponsecode);
		System.out.println(InfRes.body().asString());
	}
	
	
	
}
