package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(DemoApplication.class, args);
		
		
		
		//web dev web sql 
//		Student stud = context.getBean(Student.class);
//		stud.study();
//		
//		Car car = context.getBean(Car.class);
//		car.driver();
	}
	
	//internal flow 
	//application start -> spring scan -> component found -> 
	//bean -> store ioc container -> getbean -> return object -> method call
	
	//@repository-> database query 
	
	//@service -> business logic
	
	//@controller -> creating api 
	
	//what is dependency injection
	
	//@autowired -> dependency inject krne keliyr 
	
	//@springBootApplication -> config, scan, auto-config
	//@configuration
	//@componentScan -> scanning anotations -> servivce, controller, repo,component
	//@EnableAutoconfiguration-> spring boot automatic configuration provide karta hai
	
	
	
	//SpringApplication.run() spring boot application ko start karta hai
	
	//api -> Application programming interface 
	//ek medium hai jo 2 applications ko appse mein connect kr deta hai
	
	//api client and server ke beech ka waiter hai
	
	//client -> jo request bhejta hai 
	//ex-> browser, aur applications
	//
	
	//server -> request receive krta hai aur response send krta hai
	//ex -> springboot Applications
	
	
	
	//client -> request -> server (Springboot application) -> response -> client
	
	
	//Rest Api ->
	//rest is a web service that sends and receives data using http
	
	//browser and server ke beech communication karne wali api ko restApi bolte hai
	
	//@controller
	
	//controller request receive krta hai aur response send krta hai
	
	//@restcontroller 
	

	//@getmapping 
	
	//handelling get request 
	
	//@getMappiing("/student")
	//function
	
	
	//@RequestMapping
	//ruquestmapping use to map url
	
	//kisi url ko kis class ya method se connect krna hai
	
	//@pathvariable
	//url se value lene ke liye use karte hai 
	
	//url ke andar jo value aati hai usko receive karn
	
	
	//browser -> tecaher/id ->getmapping -> pathvaraible -> id -> return response 
	
	
	//@Request param -> request param url ke end se value receive krta hai
	//url ke question mark ? ke baad jo value aati hai usko lena 
	
	
	//pathvariable-> url ke under ,mandatory  , teacher/1
	//requestParam-> url ke end , mostly optional, /teacher/serach?variablename=value
	
	
	//post request server ko data bhejne ke liye use krte hai
	
	//name = value
	//age = 33
	//city =nagpur 
	//get  -> data fetch, url mein data , browser me visible  
	//post -> data save, body mein data , hidden 
	
	
	//@postmapping -> post request handel 
	
	//jb cleint data bhejta hai us request ko handel krne ke liye @POSTMAPPING
		//krte hai
	
	
	//@RequestBody -> request body ke data ko java object me convert karta hai
	
//	{
//		"id":1,
//		"name":"student name"
//		
//	}
	
	
	//postman -> send json data -> springboot -> @requestbody -> convert json to object-> student object ready
	
	
	//client -> send request (json) -> @post -> requestbody-> json to object 
	//-> method execute -> return object -> java object -> response 
	
	//cors -> cross origin resource sharing 
	//ek application dusri application ko access krta hai 
	//ip -> website -> seprate domain
	
	
	//client -> 
	
	
	
	
//entity -> create table 
	//id -> primary value 
	//generated value -> automatic created values
	
	
	
	
	//validation -> checking whether the user has entered correct data before saving
	
	
	
	//lombok -> is a depenedency used to reduce boilerplate code
	
	//@Getter -> generate getter method 
	//@setter -> generate setter methods
	
	//@NoArgsContructur -> create default constructor
	
	//@AllargsConstructor -> crete parametetrized constructor 
	
	//@Data -> combination of all lombok anotations 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
