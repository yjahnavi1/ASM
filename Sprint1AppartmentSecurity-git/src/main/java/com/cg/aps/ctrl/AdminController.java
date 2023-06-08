package com.cg.aps.ctrl;

import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.cg.aps.entities.AdminLogin;
import com.cg.aps.entities.Delivery;
import com.cg.aps.entities.DomesticHelp;
import com.cg.aps.entities.Flat;
import com.cg.aps.entities.Guard;
import com.cg.aps.entities.User;
import com.cg.aps.entities.Vehicle;
import com.cg.aps.entities.Visitor;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.service.AdminLoginService;
import com.cg.aps.service.DeliveryService;
import com.cg.aps.service.DomesticHelpService;
import com.cg.aps.service.FlatService;
//import com.cg.aps.service.DeliveryService;
//import com.cg.aps.service.DomesticHelpService;
//import com.cg.aps.service.FlatService;
import com.cg.aps.service.GuardService;
import com.cg.aps.service.UserService;
//import com.cg.aps.service.UserService;
import com.cg.aps.service.VehicleService;
import com.cg.aps.service.VisitorService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="http://localhost:4200")
public class AdminController {
	
	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private FlatService flatService;
	
	@Autowired
	private GuardService guardService;
	
	@Autowired
	private VisitorService visitorService;
	
	@Autowired
	private DomesticHelpService domesticHelpService;
	
	@Autowired
	private DeliveryService deliveryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private AdminLoginService adminLoginService;
	
	//http://localhost:8083/admin/guards 
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@PutMapping("/guards")
		public ResponseEntity<List<Guard>> updateGuard(
				@RequestBody Guard bean){
			List<Guard> guards= guardService.update(bean);
			if(guards.isEmpty())
			{
				logger.error("Guards are not available");
				return new ResponseEntity("Sorry! Guards not available!", 
						HttpStatus.NOT_FOUND);
			}
			logger.info("Guard is successfully updated");
			return new ResponseEntity<List<Guard>>(guards, HttpStatus.OK);
		}
		

		
		// http://localhost:8083/admin/guards 
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@PostMapping("/guards")
		//@RequestMapping(value ="/guards", consumes = MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json",method = RequestMethod.POST)
		public ResponseEntity<List<Guard>> insertGuard(
			@RequestBody Guard bean){ // @RequetBody translate incoming JSON guard data into Java Guard object
			List<Guard> guards= guardService.save(bean);
			if(guards.isEmpty())
			{
				return new ResponseEntity("Sorry! Guards not available!", 
						HttpStatus.NOT_FOUND);
			}
			logger.info("Guard is successfully added");
			return new ResponseEntity<List<Guard>>(guards, HttpStatus.OK);
		}	
		
		
		//http://localhost:8083/admin/guards/1008 
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@DeleteMapping("/guards/{guardId}")
		public ResponseEntity<List<Guard>> deleteGuard(
				@PathVariable("guardId")String guardId){
			List<Guard> guards= guardService.deleteById(guardId);
			if(guards.isEmpty() || guards==null) {
				return new ResponseEntity("Sorry! GuardId not available!", 
						HttpStatus.NOT_FOUND);
			}
			logger.info("Guard is successfully deleted");
			return new ResponseEntity<List<Guard>>(guards, HttpStatus.OK);
		}
		
		//http://localhost:8083/admin/guards/101 
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@GetMapping("/guards/{guardId}")
		public ResponseEntity<Guard> findGuard(
				@PathVariable("guardId")String guardId) throws RecordNotFoundException{
			Guard guard= guardService.findGuard(guardId);
			if(guard==null) {
				return new ResponseEntity("Sorry! Guards not found!", 
						HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<Guard>(guard, HttpStatus.OK);
		}
		// http://localhost:8083/admin/guards
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@GetMapping("/guards") //@RequestMapping(value = "/guards",method = RequestMethod.GET)
		public ResponseEntity<List<Guard>> getAllGuards(){
			List<Guard> guards= guardService.getAllGuards();
			if(guards.isEmpty()) {
				return new ResponseEntity("Sorry! Guards not available!", 
						HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<List<Guard>>(guards, HttpStatus.OK);
		}
		
		//http://localhost:8083/admin/guards/byGuardName/xyz
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@GetMapping("/guards/byGuardName/{name}") 
		public ResponseEntity<List<Guard>> getAllGuardsByName(@PathVariable String name){
			List<Guard> guards=guardService.getByName(name);
			if(guards.isEmpty()) {
				return new ResponseEntity("Sorry! Guards not available!", 
						HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<List<Guard>>(guards, HttpStatus.OK);
		}
		
		
		
		/*------------Domestic Help Record-------------*/
		
		//http://localhost:8083/admin/helps 
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@PutMapping("/helps")
				public ResponseEntity<List<DomesticHelp>> updateDomesticHelpRecord(
						@RequestBody DomesticHelp bean){
					List<DomesticHelp> beans= domesticHelpService.updateDomesticHelpRecord(bean);
					if(beans.isEmpty())
					{
						logger.error("DomesticHelpRecords are not available");
						return new ResponseEntity("Sorry! DomesticHelpRecords not available!", 
								HttpStatus.NOT_FOUND);
					}
					logger.info("Guard is successfully updated");
					return new ResponseEntity<List<DomesticHelp>>(beans, HttpStatus.OK);
				}
				

				
				// http://localhost:8083/admin/helps
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@PostMapping("/helps")
				//@RequestMapping(value ="/post", consumes = MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json",method = RequestMethod.POST)
				public ResponseEntity<List<DomesticHelp>> insertDomesticHelpRecord(
					@RequestBody DomesticHelp bean){ // @RequetBody translate incoming JSON guard data into Java Guard object
					List<DomesticHelp> beans= domesticHelpService.save(bean);
					if(beans.isEmpty())
					{
						return new ResponseEntity("Sorry! DomesticHelpRecords not available!", 
								HttpStatus.NOT_FOUND);
					}
					logger.info("DomesticHelpRecord is successfully added");
					return new ResponseEntity<List<DomesticHelp>>(beans, HttpStatus.OK);
				}	
				
				
				//http://localhost:8083/admin/helps/9347854655
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@DeleteMapping("/helps/{helperId}")
				public ResponseEntity<List<DomesticHelp>> deleteDomesticHelpRecord(
						@PathVariable("helperId")String helperId){
					List<DomesticHelp> beans= domesticHelpService.deleteDomesticHelpRecordById(helperId);
					if(beans.isEmpty() || beans==null) {
						return new ResponseEntity("Sorry! helperId not available!", 
								HttpStatus.NOT_FOUND);
					}
					logger.info("DomesticHelpRecord is successfully deleted");
					return new ResponseEntity<List<DomesticHelp>>(beans, HttpStatus.OK);
				}
				
				//http://localhost:8083/admin/helps/9347854655 
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@GetMapping("/helps/{helperId}")
				public ResponseEntity<DomesticHelp> findDomesticHelpRecord(
						@PathVariable("helperId")String helperId) throws RecordNotFoundException{
					DomesticHelp bean= domesticHelpService.findDomesticHelpRecordById(helperId);
					if(bean==null) {
						return new ResponseEntity("Sorry! DomesticHelpRecord not found!", 
								HttpStatus.NOT_FOUND);
					}
					
					return new ResponseEntity<DomesticHelp>(bean, HttpStatus.OK);
				}
				// http://localhost:8083/admin/helps
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@GetMapping("/helps") //@RequestMapping(value = "/get",method = RequestMethod.GET)
				public ResponseEntity<List<DomesticHelp>> getAllDomesticHelpRecords(){
					List<DomesticHelp> beans= domesticHelpService.getAllDomesticHelpRecords();
					if(beans.isEmpty()) {
						return new ResponseEntity("Sorry! DomesticHelpRecords not available!", 
								HttpStatus.NOT_FOUND);
					}
					
					return new ResponseEntity<List<DomesticHelp>>(beans, HttpStatus.OK);
				}
				
				//http://localhost:8083/admin/helps/byHelperName/xyz
//				@SuppressWarnings({ "unchecked", "rawtypes" })
//				@GetMapping("/helps/byHelperName/{helperName}") 
//				public ResponseEntity<List<DomesticHelp>> getAllRecordsByHelperName(@PathVariable String helperName){
//					List<DomesticHelp> beans=domesticHelpService.findRecordByHelperName(helperName);
//					if(beans.isEmpty()) {
//						return new ResponseEntity("Sorry! DomesticHelpRecords not available!", 
//								HttpStatus.NOT_FOUND);
//					}
//					
//					return new ResponseEntity<List<DomesticHelp>>(beans, HttpStatus.OK);
//				}
				
	/*---------Visitor Controller -------------*/
				//http://localhost:8083/admin/visitors
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@PutMapping("/visitors")
				public ResponseEntity<List<Visitor>> updateVisitor(
						@RequestBody Visitor bean){
					List<Visitor> visitors= visitorService.update(bean);
					if(visitors.isEmpty())
					{
						logger.error("Visitors are not available");
						return new ResponseEntity("Sorry! Visitors not available!", 
								HttpStatus.NOT_FOUND);
					}
					logger.info("Visitor is successfully updated");
					return new ResponseEntity<List<Visitor>>(visitors, HttpStatus.OK);
				}
				

				
				// http://localhost:8083/admin/visitors 
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@PostMapping("/visitors")
				//@RequestMapping(value ="/visitors", consumes = MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json",method = RequestMethod.POST)
				public ResponseEntity<List<Visitor>> insertVisitor(
					@RequestBody Visitor bean){ // @RequestBody translate incoming JSON guard data into Java Guard object
					List<Visitor> visitors= visitorService.save(bean);
					if(visitors.isEmpty())
					{
						return new ResponseEntity("Sorry! Visitors not available!", 
								HttpStatus.NOT_FOUND);
					}
					logger.info("Visitor is successfully added");
					return new ResponseEntity<List<Visitor>>(visitors, HttpStatus.OK);
				}	
				
				
				//http://localhost:8083/admin/visitors/9347854655 
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@DeleteMapping("/visitors/{visitorMobileNo}")
				public ResponseEntity<List<Visitor>> deleteVisitor(
						@PathVariable("visitorMobileNo")String visitorMobileNo){
					List<Visitor> visitors= visitorService.deleteById(visitorMobileNo);
					if(visitors.isEmpty() || visitors==null) {
						return new ResponseEntity("Sorry! visitorMobileNo not available!", 
								HttpStatus.NOT_FOUND);
					}
					logger.info("Visitor is successfully deleted");
					return new ResponseEntity<List<Visitor>>(visitors, HttpStatus.OK);
				}
				
				//http://localhost:8083/admin/visitors/9347854656
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@GetMapping("/visitors/{visitorMobileNo}")
				public ResponseEntity<Visitor> findById(
						@PathVariable("visitorMobileNo")String visitorMobileNo)throws RecordNotFoundException{
					Visitor visitor= visitorService.findById(visitorMobileNo);
					if(visitor==null) {
						return new ResponseEntity("Sorry! visitors not found!", 
								HttpStatus.NOT_FOUND);
					}
					
					return new ResponseEntity<Visitor>(visitor, HttpStatus.OK);
				}
				// http://localhost:8083/admin/visitors
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@GetMapping("/visitors") //@RequestMapping(value = "/visitors",method = RequestMethod.GET)
				public ResponseEntity<List<Visitor>> getAllVisitor(){
					List<Visitor> visitors= visitorService.getAllVisitorRecords();
					if(visitors.isEmpty()) {
						return new ResponseEntity("Sorry! Visitors not available!", 
								HttpStatus.NOT_FOUND);
					}
					
					return new ResponseEntity<List<Visitor>>(visitors, HttpStatus.OK);
				}
				
				//http://localhost:8083/admin/visitors/byVisitorName/xyz
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@GetMapping("/visitors/byVisitorName/{visitorName}") 
				public ResponseEntity<List<Visitor>> getAllVisitorByName(@PathVariable String name){
					List<Visitor> visitors=visitorService.getByVisitorName(name);
					if(visitors.isEmpty()) {
						return new ResponseEntity("Sorry! Visitors not available!", 
								HttpStatus.NOT_FOUND);
					}
					
					return new ResponseEntity<List<Visitor>>(visitors, HttpStatus.OK);
				}
				
	/*---------Vehicle Controller--------------*/
				//http://localhost:8083/admin/vehicle
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@PutMapping("/vehicles")
				public ResponseEntity<List<Vehicle>> updateVehicle(@RequestBody Vehicle bean) {
					List<Vehicle> vehicles = vehicleService.update(bean);
					if (vehicles.isEmpty()) {
						logger.error("Vehicles are not available");
						return new ResponseEntity("Sorry! Vehicles not available!", HttpStatus.NOT_FOUND);
					}
					logger.info("Vehicle is successfully updated");
					return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
				}

			// http://localhost:8083/admin/vehicles
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@PostMapping("/vehicles")
			//@RequestMapping(value ="/vehicles", consumes = MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json",method = RequestMethod.POST)
				public ResponseEntity<List<Vehicle>> insertVehicle(@RequestBody Vehicle bean) { // @RequetBody translate incoming
																								// JSON guard data into Java Guard
																								// object
					List<Vehicle> vehicles = vehicleService.save(bean);
					if (vehicles.isEmpty()) {
						return new ResponseEntity("Sorry! Vehicles not available!", HttpStatus.NOT_FOUND);
					}
					logger.info("Vehicle is successfully added");
					return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
				}

			//http://localhost:8083/admin/vehicles/1008 
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@DeleteMapping("/vehicles/{vehicleNo}")
				public ResponseEntity<List<Vehicle>> deleteVehicle(@PathVariable("vehicleNo") String vehicleNo) {
					List<Vehicle> vehicles = vehicleService.deleteById(vehicleNo);
					if (vehicles.isEmpty() || vehicles == null) {
						return new ResponseEntity("Sorry! VehicleId not available!", HttpStatus.NOT_FOUND);
					}
					logger.info("Vehicle is successfully deleted");
					return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
				}

			//http://localhost:8083/admin/vehicles/101 
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@GetMapping("/vehicles/{vehicleNo}")
			public ResponseEntity<Vehicle> findVehicle(

					@PathVariable("vehicleNo")String vehicleNo) throws RecordNotFoundException{
				Vehicle vehicle= vehicleService.findById(vehicleNo);
				if(vehicle==null) {
					return new ResponseEntity("Sorry! vehicles not found!",
							HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
			}
			 //http://localhost:8083/admin/vehicles
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@GetMapping("/vehicles") // @RequestMapping(value = "/vehicles",method = RequestMethod.GET)
				public ResponseEntity<List<Vehicle>> getAllVehicles() {
					List<Vehicle> vehicles = vehicleService.getAllVehicleRecords();
					if (vehicles.isEmpty()) {
						return new ResponseEntity("Sorry! Vehicles not available!", HttpStatus.NOT_FOUND);
					}
					return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
				}

			//http://localhost:8083/admin/vehicles/byName/xyz
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@GetMapping("/vehicles/byNo/{no}")
				public ResponseEntity<List<Vehicle>> getAllVehicleByName(@PathVariable String no) {
					List<Vehicle> vehicles = vehicleService.getByVehicleNo(no);
					if (vehicles.isEmpty()) {
						return new ResponseEntity("Sorry! Vehicles not available!", HttpStatus.NOT_FOUND);
					}
					return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
				}
				
	/*------------Flat Controller---------------*/
				
				//http://localhost:8083/admin/flats 
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@PutMapping("/flats")
				public ResponseEntity<List<Flat>> updateFlat(
						@RequestBody Flat bean){
					List<Flat> flats= flatService.update(bean);
					if(flats.isEmpty())
					{
						logger.error("Flats are not available");
						return new ResponseEntity("Sorry! Flats not available!", 
								HttpStatus.NOT_FOUND);
					}
					logger.info("Flat is successfully updated");
					return new ResponseEntity<List<Flat>>(flats, HttpStatus.OK);
				}
				

				
				// http://localhost:8083/admin/flats 
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@PostMapping("/flats")
				//@RequestMapping(value ="/flats", consumes = MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json",method = RequestMethod.POST)
				public ResponseEntity<List<Flat>> insertFlat(
					@RequestBody Flat bean){ // @RequetBody translate incoming JSON flat data into Java Flat object
					List<Flat> flats= flatService.save(bean);
					if(flats.isEmpty())
					{
						return new ResponseEntity("Sorry! Flats not available!", 
								HttpStatus.NOT_FOUND);
					}
					logger.info("Flat is successfully added");
					return new ResponseEntity<List<Flat>>(flats, HttpStatus.OK);
				}	
				
				
				//http://localhost:8083/admin/flats/1008 
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@DeleteMapping("/flats/{flatNo}")
				public ResponseEntity<List<Flat>> deleteFlat(
						@PathVariable("flatNo")String flatNo){
					List<Flat> flats= flatService.deleteById(flatNo);
					if(flats.isEmpty() || flats==null) {
						return new ResponseEntity("Sorry! FlatNo not available!", 
								HttpStatus.NOT_FOUND);
					}
					logger.info("Flat is successfully deleted");
					return new ResponseEntity<List<Flat>>(flats, HttpStatus.OK);
				}
				
				/* %%%%%%%%% Number of flats visited by visitor %%%%%%%%%% */
				//http://localhost:8083/admin/flats/countOf/12c 
//				@SuppressWarnings({ "rawtypes", "unchecked" })
//				@GetMapping("/flats/countOf/{visitorId}")
//				public ResponseEntity<List<Flat>> flatsVisitedByVisitor(
//						@PathVariable("visitorId")String visitorId) throws RecordNotFoundException{
//					List<Flat> flats= visitorService.flatsVisitedByVisitor(visitorId);
//					if(flats==null) {
//						return new ResponseEntity("Sorry! Flats not found!", 
//								HttpStatus.NOT_FOUND);
//					}
//					
//					return new ResponseEntity<List<Flat>>(flats, HttpStatus.OK);
//				}
				
				//http://localhost:8083/admin/flats/101 
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@GetMapping("/flats/{flatNo}")
				public ResponseEntity<Flat> findFlat(
						@PathVariable("flatNo")String flatNo) throws RecordNotFoundException{
					Flat flat= flatService.findFlat(flatNo);
					if(flat==null) {
						return new ResponseEntity("Sorry! Flats not found!", 
								HttpStatus.NOT_FOUND);
					}
					
					return new ResponseEntity<Flat>(flat, HttpStatus.OK);
				}
				// http://localhost:8083/admin/flats
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@GetMapping("/flats") //@RequestMapping(value = "/flats",method = RequestMethod.GET)
				public ResponseEntity<List<Flat>> getAllFlats(){
					List<Flat> flats= flatService.getAllFlatRecords();
					if(flats.isEmpty()) {
						return new ResponseEntity("Sorry! Flats not available!", 
								HttpStatus.NOT_FOUND);
					}
					
					return new ResponseEntity<List<Flat>>(flats, HttpStatus.OK);
				}
				
				
				
				//http://localhost:8083/admin/flats/byownerName/xyz
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@GetMapping("/flats/byownerName/{name}") 
				public ResponseEntity<List<Flat>> getAllFlatsByownerName(@PathVariable String  ownerName){
					List<Flat> flats=(List<Flat>) flatService.getByName(ownerName);
					if(flats.isEmpty()) {
						return new ResponseEntity("Sorry! Flats not available!", 
								HttpStatus.NOT_FOUND);
					}
					
					return new ResponseEntity<List<Flat>>(flats, HttpStatus.OK);
				}


	/*----------Delivery Controller-------------*/
				
				// http://localhost:8083/deliverymanagement/delivery
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@PutMapping("/delivery")
				public ResponseEntity<List<Delivery>> updateDelivery(@RequestBody Delivery bean) {
					List<Delivery> delivery = deliveryService.update(bean);
					if (delivery.isEmpty()) {
						logger.error("Delivery is not available");
						return new ResponseEntity("Sorry! Delivery not available!", HttpStatus.NOT_FOUND);
					}
					logger.info("Delivery is successfully updated");
					return new ResponseEntity<List<Delivery>>(delivery, HttpStatus.OK);
				}

				// http://localhost:8083/deliverymanagement/delivery
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@PostMapping("/delivery")
				//@RequestMapping(value ="/delivery", consumes =
				// MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json",method =
				// RequestMethod.POST)
				public ResponseEntity<List<Delivery>> insertDelivery(@RequestBody Delivery bean) { // @RequetBody translate incoming
																									// JSON guard data into Java
																									// Guard object
			List<Delivery> delivery = deliveryService.save(bean);
					if (delivery.isEmpty()) {
						return new ResponseEntity("Sorry! Delivery not available!", HttpStatus.NOT_FOUND);
					}
					logger.info("Delivery is successfully added");
					return new ResponseEntity<List<Delivery>>(delivery, HttpStatus.OK);
				}

			//http://localhost:8083/deliverymanagement/delivery/1008 
				 @SuppressWarnings({ "rawtypes", "unchecked" })
				 @DeleteMapping("/delivery/{deliveryId}")
				public ResponseEntity<List<Delivery>> deleteDelivery(@PathVariable("deliveryId") String orderId) {
					List<Delivery> delivery = deliveryService.deleteById(orderId);
					if (delivery.isEmpty() || delivery == null) {
						return new ResponseEntity("Sorry! DeliveryId not available!", HttpStatus.NOT_FOUND);
					}
					logger.info("Delivery is successfully deleted");
					return new ResponseEntity<List<Delivery>>(delivery, HttpStatus.OK);
				}

				// http://localhost:8083/deliverymanagement/delivery/101
				 @SuppressWarnings({ "rawtypes", "unchecked" })
				 @GetMapping("/delivery/{deliveryId}")
				public ResponseEntity<Delivery> findDelivery(@PathVariable("deliveryId") String id)
						throws RecordNotFoundException {
					Delivery delivery = deliveryService.findDeliveryById(id);
					if (delivery == null) {
						return new ResponseEntity("Sorry! Delivery not found!", HttpStatus.NOT_FOUND);
					}
					return null;
				}

				// http://localhost:8083/deliverymanagement/delivery
				 @SuppressWarnings({ "rawtypes", "unchecked" })
				@GetMapping("/delivery") // @RequestMapping(value = "/delivery",method = RequestMethod.GET)
				public ResponseEntity<List<Delivery>> getAllDelivery() {
					List<Delivery> delivery = deliveryService.getAllDeliveryRecords();
					if (delivery.isEmpty()) {
						return new ResponseEntity("Sorry! Delivery not available!", HttpStatus.NOT_FOUND);
					}

					return new ResponseEntity<List<Delivery>>(delivery, HttpStatus.OK);
				}

				// http://localhost:8083/deliverymanagement/delivery/byDeliveryName/xyz
//				@SuppressWarnings({ "unchecked", "rawtypes" })
//				@GetMapping("/delivery/byDeliveryName/{name}")
//				public ResponseEntity<List<Delivery>> getAllDeliveryByName(@PathVariable String name) {
//					List<Delivery> delivery = deliveryService.getByName(name);
//					if (delivery.isEmpty()) {
//						return new ResponseEntity("Sorry! Guards not available!", HttpStatus.NOT_FOUND);
//					}
//
//					return new ResponseEntity<List<Delivery>>(delivery, HttpStatus.OK);
//				}
//				
	/*----------User Controller-----------*/
				
				//http://localhost:8083/admin/logins 
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@PutMapping("/logins")
				public ResponseEntity<List<User>> updateLogin(
						@RequestBody User bean){
					List<User> logins= userService.update(bean);
					if(logins.isEmpty())
					{
						logger.error("Logins are not available");
						return new ResponseEntity("Sorry! Logins not available!", 
								HttpStatus.NOT_FOUND);
					}
					logger.info("Login is successfully updated");
					return new ResponseEntity<List<User>>(logins, HttpStatus.OK);
				}
				

				
				// http://localhost:8083/admin/logins 
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@PostMapping("/logins")
				//@RequestMapping(value ="/logins", consumes = MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json",method = RequestMethod.POST)
				public ResponseEntity<List<User>> insertLogin(
					@RequestBody User bean){ // @RequetBody translate incoming JSON login data into Java Login object
					List<User> logins= userService.save(bean);
					if(logins.isEmpty())
					{
						return new ResponseEntity("Sorry! Logins not available!", 
								HttpStatus.NOT_FOUND);
					}
					logger.info("Login is successfully added");
					return new ResponseEntity<List<User>>(logins, HttpStatus.OK);
				}	
				
				
				//http://localhost:8083/admin/logins/1008 
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@DeleteMapping("/logins/{loginId}")
				public ResponseEntity<List<User>> deleteLogin(
						@PathVariable("loginId")String loginId){
					List<User> logins= userService.deleteById(loginId);
					if(logins.isEmpty() || logins==null) {
						return new ResponseEntity("Sorry! LoginId not available!", 
								HttpStatus.NOT_FOUND);
					}
					logger.info("Login is successfully deleted");
					return new ResponseEntity<List<User>>(logins, HttpStatus.OK);
				}
				
				//http://localhost:8083/admin/logins/101 
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@GetMapping("/logins/{loginId}")
				public ResponseEntity<User> findLogin(
						@PathVariable("loginId")String loginId) throws RecordNotFoundException{
					User login= userService.findUser(loginId);
					if(login==null) {
						return new ResponseEntity("Sorry! Logins not found!", 
								HttpStatus.NOT_FOUND);
					}
					
					return new ResponseEntity<User>(login, HttpStatus.OK);
				}
				// http://localhost:8083/admin/logins
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@GetMapping("/logins") //@RequestMapping(value = "/logins",method = RequestMethod.GET)
				public ResponseEntity<List<User>> getAllUserRecords(){
					List<User> logins= userService.getAllUserRecords();
					if(logins.isEmpty()) {
						return new ResponseEntity("Sorry! Logins not available!", 
								HttpStatus.NOT_FOUND);
					}
					
					return new ResponseEntity<List<User>>(logins, HttpStatus.OK);
				}
				
				//http://localhost:8083/admin/logins/byLoginFirstName/xyz
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@GetMapping("/logins/LoginbyNo/{no}") 
				public ResponseEntity<List<User>> getAllLoginsByFirstName(@PathVariable String no){
					List<User> logins=userService.getByMobileNo(no);
					if(logins.isEmpty()) {
						return new ResponseEntity("Sorry! Logins not available!", 
								HttpStatus.NOT_FOUND);
					}
					
					return new ResponseEntity<List<User>>(logins, HttpStatus.OK);
				}
				
				/*=============db connection=========*/
				// http://localhost:8083/admin/logins 
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@PostMapping("/db")
				//@RequestMapping(value ="/logins", consumes = MediaType.APPLICATION_JSON_VALUE, headers="Accept=application/json",method = RequestMethod.POST)
				public ResponseEntity<AdminLogin> adminLoginPost(
					@RequestBody AdminLogin bean){ // @RequetBody translate incoming JSON login data into Java Login object
					List<AdminLogin> login= adminLoginService.save(bean);
					List<User> logins= userService.getAllUserRecords();
					ListIterator<User> ltr = logins.listIterator();
					while(ltr.hasNext()) {
						User u = ltr.next();
						if((u.getLoginId()==bean.getLoginId())&& (u.getPassword()==bean.getPassword())) {
							return new ResponseEntity<AdminLogin>(bean, HttpStatus.OK);
						}
					}
					
					if(logins.isEmpty())
					{
						return new ResponseEntity(null, 
								HttpStatus.NOT_FOUND);
					}
					//logger.info("Login is successfully added");
					bean = null;
					return new ResponseEntity<AdminLogin>(bean, HttpStatus.OK);
				}	
				
				
				// http://localhost:8083/admin/logins
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@GetMapping("/db") //@RequestMapping(value = "/logins",method = RequestMethod.GET)
				public ResponseEntity<List<AdminLogin>> getAlladminLogins(){
					List<AdminLogin> logins= adminLoginService.getAllAdminLogins();
					if(logins.isEmpty()) {
						return new ResponseEntity("Sorry! Logins not available!", 
								HttpStatus.NOT_FOUND);
					}
					
					return new ResponseEntity<List<AdminLogin>>(logins, HttpStatus.OK);
				}
				
						
				
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@ExceptionHandler(RecordNotFoundException.class) // local to the RestController
				public final ResponseEntity<String> handleExceptions(Exception ex, WebRequest request) {
				 logger.error(ex.getMessage());
				 return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}


}
