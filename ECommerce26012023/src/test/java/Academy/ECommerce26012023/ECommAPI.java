package Academy.ECommerce26012023;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import Academy.POJO.CreateOrderRequest;
import Academy.POJO.CreateProductResponse;
import Academy.POJO.LoginRequest;
import Academy.POJO.LoginResponse;
import Academy.POJO.OrderDetailsResponse;
import Academy.POJO.OrdersPOJO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class ECommAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Steps for this assignment
		//Create RequestSpecification requestSB, create LoginAPI REST, for body use POJO, response get as a POJO, not as a string, 
		//CreateProduct API, 
		
		//Request Spec builder
		RequestSpecification requestSBBase = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		// addHeader("Content-Type", "application/json")
		
		
		LoginRequest lrPOJO = new LoginRequest();
		lrPOJO.setUserEmail("unknownxjk@gmail.com");
		lrPOJO.setUserPassword("kojikurac123");
		
		//01. LoginAPI
		RequestSpecification requestSBLogin = given().log().all().spec(requestSBBase).body(lrPOJO);
		
		LoginResponse lresponsePOJO = requestSBLogin.when().post("/api/ecom/auth/login")
		.then().log().all().assertThat().statusCode(200).extract().response()/*.asString()*/.as(LoginResponse.class);
		
		String userToken = lresponsePOJO.getToken();
		String userId = lresponsePOJO.getUserId();
		
		System.out.println(userToken);
		System.out.println(userId);
		
		
		//02. CreateProductAPI
		
		RequestSpecification requestSBCreateProductBase = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com")
				.addHeader("Authorization", userToken).build();
		//Here Content -Type is not JSON, we have form data therefore we use .addHeader("Authorization", userToken)
		
		RequestSpecification requestSBCreateProduct = given().log().all().spec(requestSBCreateProductBase).param("productName", "jersey")
		.param("productAddedBy", userId)
		.param("productCategory", "fashion")
		.param("productSubCategory", "shirts")
		.param("productPrice", "114")
		.param("productDescription", "Reebok originals")
		.param("productFor", "men")
		.multiPart("productImage", new File("C:\\Users\\joko2909\\Desktop\\Prntscr\\nba.png")); //This is for attached file, under given()
		
		
		CreateProductResponse cpResponsePOJO = requestSBCreateProduct.when().post("/api/ecom/product/add-product")
		.then().log().all().assertThat().statusCode(201).extract().response().as(CreateProductResponse.class);
		
		String productId = cpResponsePOJO.getProductId();
		System.out.println(productId);
		
		
		//03. CreateOrderAPI
		
		OrdersPOJO ordersPojo =  new OrdersPOJO();
		ordersPojo.setCountry("Yugoslavia");
		ordersPojo.setProductOrderedId(productId);
		
		List<OrdersPOJO> myList = new ArrayList<>(); //ArrayList<OrdersPOJO>() as per Rahul
		myList.add(ordersPojo);
		
		CreateOrderRequest corPOJO = new CreateOrderRequest();
		corPOJO.setOrders(myList);
		
		
		RequestSpecification requestSBCreateOrderBase = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com").setContentType(ContentType.JSON)
				.addHeader("Authorization", userToken).build();
		
		RequestSpecification requestSBCreateOrder = given().urlEncodingEnabled(false).log().all().spec(requestSBCreateOrderBase).body(corPOJO);
		
		String responseCreateOrder = requestSBCreateOrder.when().post("/api/ecom/order/create-order")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath jp01 = new JsonPath(responseCreateOrder);
		String ordersId = jp01.getString("orders");
		System.out.println(ordersId);
		
		
		String[] splitted01 = ordersId.split("\\[");
		String[] splitted02 = splitted01[1].split("\\]");
		String ordersIdSplitted = splitted02[0].trim();
		System.out.println(ordersIdSplitted);
		
		
		
		
		//04. OrderDetailsAPI
	/*	
		RequestSpecification requestSBOrderDetailsBase = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com")
				.addHeader("Authorization", userToken).build();
		
		RequestSpecification requestSBOrderDetails = given().urlEncodingEnabled(false).log().all().spec(requestSBOrderDetailsBase).queryParam("id", ordersIdSplitted);
		
		String resposneOrderDetails = requestSBOrderDetails.when().get("/api/ecom/order/get-orders-details")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath jp03 = new JsonPath(resposneOrderDetails);
		String something = jp03.getString("data.orderPrice");
		System.out.println(something);*/
		
		
		//This is with a POJO class, and the one above is without
		RequestSpecification requestSBOrderDetailsBase = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com")
				.addHeader("Authorization", userToken).build();
		
		RequestSpecification requestSBOrderDetails = given().urlEncodingEnabled(false).log().all().spec(requestSBOrderDetailsBase).queryParam("id", ordersIdSplitted);
		
		
		OrderDetailsResponse odResponsePOJO =  requestSBOrderDetails.when().get("/api/ecom/order/get-orders-details")
		.then().log().all().assertThat().statusCode(200).extract().response().as(OrderDetailsResponse.class);
		
		String orderBy = odResponsePOJO.getData().getOrderBy();
		String orderPrice = odResponsePOJO.getData().getOrderPrice();
		System.out.println(orderBy);
		System.out.println(orderPrice);
		
		
		//05. DeleteProductAPI
		
		
		RequestSpecification requestSBDeleteProductBase = new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com")
				.addHeader("Authorization", userToken).build();
		
		RequestSpecification requestSBDeleteProduct = given().relaxedHTTPSValidation().log().all().spec(requestSBDeleteProductBase).pathParam("productIdJK", productId);
		
		String resposneDeleteProduct = requestSBDeleteProduct.when().delete("/api/ecom/product/delete-product/{productIdJK}")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		String expectedMessage = "Product Deleted Successfully";
		JsonPath jp02 = new JsonPath(resposneDeleteProduct);
		String messageResponse = jp02.getString("message");
		System.out.println(messageResponse);
		
		Assert.assertEquals(messageResponse, expectedMessage);

		
		
		
	}

}
