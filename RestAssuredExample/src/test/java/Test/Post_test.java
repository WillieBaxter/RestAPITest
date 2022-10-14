package Test;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Post_test {
	
	public static void main(String[] args) {
		deleteTest();
		postPositiveTest();
	    getPositiveRequest();
		getNegativeRequest();
		postNegativeTest();
		getID();
	}
	
	public static void postPositiveTest() {
		
		System.out.println("Positive POST Status Code: 200");
		JSONObject request = new JSONObject();
		request.put("sku", "Sony");
		request.put("description", "Walkman");
		request.put("price", "5.99");
	
		given().body(request.toJSONString()).
		when().post("https://1ryu4whyek.execute-api.us-west-2.amazonaws.com/dev/skus").
		then().statusCode(200).log().all();
	

	}
	
	
	
    public static void postNegativeTest() {
		
    	System.out.println("Post Negative Status Code: 300");
		JSONObject request = new JSONObject();
		request.put("sku", "Sony");
		request.put("description", "Walkman");
		request.put("price", "5.99");
		
		given().body(request.toJSONString()).
		when().post("https://1ryu4whyek.execute-api.us-west-2.amazonaws.com/dev/skus").
		then().statusCode(300).log().all();
	
	}
	
	public static void getPositiveRequest() {
		System.out.println("Positive GET Status Code: 200");
		String url = "https://1ryu4whyek.execute-api.us-west-2.amazonaws.com/dev/skus";
		given().get(url).then().statusCode(200).log().all();
	}

	public static void getNegativeRequest() {		
		System.out.println("Negative GET Status Code: 201");
		String url = "https://1ryu4whyek.execute-api.us-west-2.amazonaws.com/dev/skus";
		given().get(url).then().statusCode(201).log().all();
		
	}
	
	public static void getID() {
		System.out.println("Negative GET ID Status Code: Not Matching");
		given().get("https://1ryu4whyek.execute-api.us-west-2.amazonaws.com/dev/skus/%7bid%7d")
		.then().statusCode(200).body("ResponseMetadata.RequestId[0]", equalTo("2S90C8H7FPKA7GAQ45QFB5DJSNVV4KQNSO5AEMVJF66Q9ASUAAJG")).log().all();
		
	}

	public static void deleteTest() {
		System.out.println("Delete Status Code: 200");
		when().delete("https://1ryu4whyek.execute-api.us-west-2.amazonaws.com/dev/skus/%7bid%7d").then().statusCode(200).log().all();
	}
}
