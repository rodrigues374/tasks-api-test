package br.ce.wcaquino.tasks.apitest;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ApiTest {

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";		
		
	}
		
	@Test
	public void deveRetornarTarefas() {
		
		RestAssured.given()
		.when()
		.get("/todo")
		.then()	
		.statusCode(200);
	}
	
	@Test
	public void deveAdicionarTarefasComSucesso() {
	 
		RestAssured.given()
		.contentType(ContentType.JSON)
		.body("{\"task\":\"Teste via Api\", \"dueDate\":\"2021-12-30\"}")
		.when()
		.post("/todo")
		.then()
		.statusCode(201);
	}
	
	@Test
	public void deveAdicionarTarefasInvalida() {
	 
		RestAssured.given()
		.contentType(ContentType.JSON)
		.body("{\"task\":\"Teste via Api\", \"dueDate\":\"2020-12-30\"}")
		.when()
		.post("/todo")
		.then()
		.statusCode(400)
		.body("message", CoreMatchers.is("Due date must not be in past"));
	}
}

