//package com.cg.aps;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.web.client.RestTemplate;
//
//import com.cg.aps.entities.Guard;
//
//
//
//@SpringBootTest
////@ContextConfiguration(classes = Sprint1AppartmentSecurityApplication.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class GuardControllerTest {
//	Logger logger = LoggerFactory.getLogger(GuardControllerTest.class);
//	
//	private RestTemplate restTemplate;
//	private static final String guardResourceUrl="http://localhost:8083/admin/guards";
//	
//	@BeforeEach
//	public void setup() {
//		restTemplate=new RestTemplate();
//	}
//	
//	@Test
//	@Order(1)
//	void testfindById() {
//		logger.info("Running find by id url");
//		
//		String url=guardResourceUrl+"/9347854655";
//		final ResponseEntity<Guard> response = restTemplate.getForEntity(url, Guard.class);
//		Guard p=response.getBody();
//		System.out.println(p.getGuardMobileNo());
//		assertEquals("9347854655",p.getGuardMobileNo());
//		assertEquals(response.getStatusCode(), HttpStatus.OK);
//		
//	}
//	@Test
//	@Order(2)
//	void testfindById1() {
//		logger.info("Running find by id url as Json");
//		
//		String url=guardResourceUrl+"/9347854655";
//		final ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//		String responseBody=response.getBody();
//		logger.info(responseBody);
//		
//		JSONParser parser = new JSONParser();
//		JSONObject jsonResponseObject = new JSONObject();
//		Object obj = new Object();
//		try {
//			obj = parser.parse(responseBody);
//		}
//		catch (org.json.simple.parser.ParseException e) {
//			e.printStackTrace();
//		}
//		jsonResponseObject = (JSONObject) obj;
//		logger.info(jsonResponseObject.toJSONString());
//		
//		assertEquals(response.getStatusCode(), HttpStatus.OK);
//		assertEquals("King",jsonResponseObject.get("guardName"));
//	}
//	
//	@Test
//	@Order(3)
//	void testfindById2() {
//		logger.info("Running find by id url as Guard object");
//		
//		String url=guardResourceUrl+"/9347854655";
//		final Guard p = restTemplate.getForObject(url, Guard.class);
//		
//		logger.info(p.toString());
//		assertEquals("King",p.getGuardName());
//	}
//	
//	@Test
//	@Order(4)
//	public void testAddGuard() {
//		logger.info("Running add Guard url test using postForEntity");
//		
//		String url=guardResourceUrl;
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Accept", "application/json");
//		headers.add("Content-Type", "application/json");
//		String jsonBody = "{\"guardMobileNo\":\"9347854655\",\"guardName\":\"King\",\"shiftTime\":\"12:10\",\"date\":\"11-Mar-2025\"}";
//		HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
//		//POST Method to Add New Guard
//		ResponseEntity<String> response = this.restTemplate.postForEntity(url, entity, String.class);
//		String responseBody=response.getBody();
//		logger.info(responseBody);
//		
//		assertEquals(response.getStatusCode(), HttpStatus.OK);
//	}
//	
//	@Test
//	@Order(5)
//	public void testAddGuard1() {
//		logger.info("Running add Guard url test using postForObject");
//
//		String url=guardResourceUrl;
//		Guard p=new Guard("Jhon","9347854655",LocalTime.of(12,30,00),LocalDate.of(2023, 3, 31));
//		HttpEntity<Guard> entity = new HttpEntity<Guard>(p);
//		//POST Method to Add New Guard
//		String response = this.restTemplate.postForObject(url, entity, String.class);
//		
//		logger.info(response);
//		assertNotNull(response);
//	}
//	
//	@Test
//	@Order(6)
//	public void testUpdateGuard() {
//		logger.info("Running update Guard url test using exchange");
//	
//		String url=guardResourceUrl;
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Accept", "application/json");
//		headers.add("Content-Type", "application/json");
//		String jsonBody = "{\"guardMobileNo\":\"9347854655\",\"guardName\":\"King\",\"shiftTime\":\"12:10\",\"date\":\"11-Mar-2025\"}";
//		
//		logger.info(jsonBody);
//		HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
//		//POST Method to Add New Guard
//		ResponseEntity<String> response = this.restTemplate.exchange(url,HttpMethod.PUT, entity, String.class);
//		String responseBody=response.getBody();
//		logger.info(responseBody);
//		
//		assertEquals(response.getStatusCode(), HttpStatus.OK);
//		assertTrue(responseBody.contains("9347854655"));
//	}
//	
//	@Test
//	@Order(7)
//	public void testDeleteGuard() {
//		logger.info("Running delete Guard url test using exchange");
//		
//		String url=guardResourceUrl+"/9347854656";
//		HttpHeaders headers = new HttpHeaders();
//		HttpEntity<String> entity = new HttpEntity<String>( headers);
//		ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
//		assertEquals(response.getStatusCode(), HttpStatus.OK); 
//		String responseBody = response.getBody();
//		logger.info(responseBody);
//		
//		assertFalse(responseBody.contains("9347854656"));
//	}
//}
