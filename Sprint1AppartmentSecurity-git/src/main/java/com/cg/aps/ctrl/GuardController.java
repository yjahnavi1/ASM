//package com.cg.aps.ctrl;
//
//import java.util.List;
//
//import com.cg.aps.entities.Visitor;
//import com.cg.aps.exception.RecordNotFoundException;
//import com.cg.aps.service.VisitorService;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.WebRequest;
//
//@RestController
//@RequestMapping("/guard")
//public class GuardController {
//	Logger logger = LoggerFactory.getLogger(GuardController.class);
//
//	@Autowired
//	private VisitorService visitorService;
//	
//	//http://localhost:8083/guard/visitors
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@PutMapping("/visitors")
//	public ResponseEntity<List<Visitor>> updateVisitor(
//			@RequestBody Visitor bean){
//		List<Visitor> visitors= visitorService.update(bean);
//		if(visitors.isEmpty())
//		{
//			logger.error("Visitors are not available");
//			return new ResponseEntity("Sorry! Visitors not available!", 
//					HttpStatus.NOT_FOUND);
//		}
//		logger.info("Visitor is successfully updated");
//		return new ResponseEntity<List<Visitor>>(visitors, HttpStatus.OK);
//	}
//	
//
//	
//	// http://localhost:8083/guard/visitors 
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@PostMapping("/visitors")
//	//@RequestMapping(value ="/visitors", consumes = MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json",method = RequestMethod.POST)
//	public ResponseEntity<List<Visitor>> insertVisitor(
//		@RequestBody Visitor bean){ // @RequestBody translate incoming JSON guard data into Java Guard object
//		List<Visitor> visitors= visitorService.save(bean);
//		if(visitors.isEmpty())
//		{
//			return new ResponseEntity("Sorry! Visitors not available!", 
//					HttpStatus.NOT_FOUND);
//		}
//		logger.info("Visitor is successfully added");
//		return new ResponseEntity<List<Visitor>>(visitors, HttpStatus.OK);
//	}	
//	
//	
//	//http://localhost:8083/guard/visitors/9347854655 
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@DeleteMapping("/visitors/{visitorMobileNo}")
//	public ResponseEntity<List<Visitor>> deleteVisitor(
//			@PathVariable("visitorMobileNo")String visitorMobileNo){
//		List<Visitor> visitors= visitorService.deleteById(visitorMobileNo);
//		if(visitors.isEmpty() || visitors==null) {
//			return new ResponseEntity("Sorry! visitorMobileNo not available!", 
//					HttpStatus.NOT_FOUND);
//		}
//		logger.info("Visitor is successfully deleted");
//		return new ResponseEntity<List<Visitor>>(visitors, HttpStatus.OK);
//	}
//	
//	//http://localhost:8083/guard/visitors/9347854656
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@GetMapping("/visitors/{visitorMobileNo}")
//	public ResponseEntity<Visitor> findById(
//			@PathVariable("visitorMobileNo")String visitorMobileNo)throws RecordNotFoundException{
//		Visitor visitor= visitorService.findById(visitorMobileNo);
//		if(visitor==null) {
//			return new ResponseEntity("Sorry! visitors not found!", 
//					HttpStatus.NOT_FOUND);
//		}
//		
//		return new ResponseEntity<Visitor>(visitor, HttpStatus.OK);
//	}
//	// http://localhost:8083/guard/visitors
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@GetMapping("/visitors") //@RequestMapping(value = "/visitors",method = RequestMethod.GET)
//	public ResponseEntity<List<Visitor>> getAllVisitor(){
//		List<Visitor> visitors= visitorService.getAllVisitorRecords();
//		if(visitors.isEmpty()) {
//			return new ResponseEntity("Sorry! Visitors not available!", 
//					HttpStatus.NOT_FOUND);
//		}
//		
//		return new ResponseEntity<List<Visitor>>(visitors, HttpStatus.OK);
//	}
//	
//	//http://localhost:8083/guard/visitors/byVisitorName/xyz
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@GetMapping("/visitors/byVisitorName/{visitorName}") 
//	public ResponseEntity<List<Visitor>> getAllVisitorByName(@PathVariable String name){
//		List<Visitor> visitors=visitorService.getByVisitorName(name);
//		if(visitors.isEmpty()) {
//			return new ResponseEntity("Sorry! Visitors not available!", 
//					HttpStatus.NOT_FOUND);
//		}
//		
//		return new ResponseEntity<List<Visitor>>(visitors, HttpStatus.OK);
//	}
//	
//			
//	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@ExceptionHandler(RecordNotFoundException.class) // local to the RestController
//	public final ResponseEntity<String> handleExceptions(Exception ex, WebRequest request) {
//	 logger.error(ex.getMessage());
//	 return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//}