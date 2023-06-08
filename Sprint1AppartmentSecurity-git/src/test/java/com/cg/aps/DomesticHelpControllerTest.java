//package com.cg.aps;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.sql.Date;
//import java.time.LocalDate;
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
//import com.cg.aps.SprintApplication;
//import com.cg.aps.entities.DomesticHelp;
//
//@SpringBootTest
//@ContextConfiguration(classes = SprintApplication.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class DomesticHelpControllerTest {
//
//	Logger logger = LoggerFactory.getLogger(DomesticHelpControllerTest.class);
//	
//	private RestTemplate restTemplate;
//	private static final String domestichelpResourceUrl="http://localhost:8083/domesticHelp/helps";
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
//		String url=domestichelpResourceUrl+"/4888765";
//		final ResponseEntity<DomesticHelp> response = restTemplate.getForEntity(url, DomesticHelp.class);
//		DomesticHelp p=response.getBody();
//		assertEquals("4888765",p.getHelperMobileNo());
//		assertEquals(response.getStatusCode(), HttpStatus.OK);
//		
//	}
//	@Test
//	@Order(2)
//	void testfindById1() {
//		logger.info("Running find by id url as Json");
//		
//		String url=domestichelpResourceUrl+"/4888765";
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
//		assertEquals("Raj",jsonResponseObject.get("helperName"));
//	}
//	
//	@Test
//	@Order(3)
//	void testfindById2() {
//		logger.info("Running find by id url as DomesticHelp object");
//		
//		String url=domestichelpResourceUrl+"/4888765";
//		final DomesticHelp p = restTemplate.getForObject(url, DomesticHelp.class);
//		
//		logger.info(p.toString());
//		assertEquals("Raj",p.getHelperName());
//	}
//	
//	@Test
//	@Order(4)
//	public void testAddDomesticHelpRecord() {
//		logger.info("Running add DomesticHelpRecord url test using postForEntity");
//		
//		String url=domestichelpResourceUrl;
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Accept", "application/json");
//		headers.add("Content-Type", "application/json");
//		String jsonBody = "{\"helperMobileNo\":47854655,\"helperName\":\"King1\",\"flatNo\":\"934\",\"ownerName\":\"King\",\"helpType\":\"sweeping\",\"arrivalTime\":\"21:10\",\"departureTime\":\"21:10\",\"date\":\"12-Mar-2025\"}";
//		HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
//		//POST Method to Add New DomesticHelpRecord
//		ResponseEntity<String> response = this.restTemplate.postForEntity(url, entity, String.class);
//		String responseBody=response.getBody();
//		logger.info(responseBody);
//		
//		assertEquals(response.getStatusCode(), HttpStatus.OK);
//	}
//	
//	@Test
//	@Order(5)
//	public void testAddDomesticHelpRecord1() {
//		logger.info("Running add DomesticHelpRecord url test using postForObject");
//
//		String url=domestichelpResourceUrl;
//		DomesticHelp p=new DomesticHelp("4888765","Raj","784","King5","sweeping","06:10","22:10",Date.valueOf(LocalDate.of(2023, 3, 31)));
//		HttpEntity<DomesticHelp> entity = new HttpEntity<DomesticHelp>(p);
//		//POST Method to Add New DomesticHelpRecord
//		String response = this.restTemplate.postForObject(url, entity, String.class);
//		
//		logger.info(response);
//		assertNotNull(response);
//	}
//	
//	@Test
//	@Order(6)
//	public void testUpdateDomesticHelpRecord() {
//		logger.info("Running update DomesticHelpRecord url test using exchange");
//	
//		String url=domestichelpResourceUrl;
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Accept", "application/json");
//		headers.add("Content-Type", "application/json");
//		String jsonBody = "{\"helperMobileNo\":47854655,\"helperName\":\"King1\",\"flatNo\":\"934\",\"ownerName\":\"King\",\"helpType\":\"sweeping\",\"arrivalTime\":\"21:10\",\"departureTime\":\"21:10\",\"date\":\"12-Mar-2025\"}";
//		
//		logger.info(jsonBody);
//		HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
//		//POST Method to Add New DomesticHelpRecord
//		ResponseEntity<String> response = this.restTemplate.exchange(url,HttpMethod.PUT, entity, String.class);
//		String responseBody=response.getBody();
//		logger.info(responseBody);
//		
//		assertEquals(response.getStatusCode(), HttpStatus.OK);
//		assertTrue(responseBody.contains("47854655"));
//	}
//	
//	@Test
//	@Order(7)
//	public void testDeleteDomesticHelpRecord() {
//		logger.info("Running delete DomesticHelpRecord url test using exchange");
//		
//		String url=domestichelpResourceUrl+"/9788846";
//		HttpHeaders headers = new HttpHeaders();
//		HttpEntity<String> entity = new HttpEntity<String>( headers);
//		ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
//		assertEquals(response.getStatusCode(), HttpStatus.OK); 
//		String responseBody = response.getBody();
//		logger.info(responseBody);
//		
//		assertFalse(responseBody.contains("9788846"));
//	}
//
//}
