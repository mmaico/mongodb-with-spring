package com.mkyong.core;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mkyong.config.SpringMongoConfig;
import com.mkyong.user.Address;
import com.mkyong.user.Address.Type;
import com.mkyong.user.User;

public class App {

	public static void main(String[] args) {
		// For XML
		 ApplicationContext ctx = new GenericXmlApplicationContext("mongo-config.xml");

//		// For Annotation
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(
//				SpringMongoConfig.class);

		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		User user = getUser();
//
//		// save
		mongoOperation.save(user, "test");

		// find
		User savedUser = mongoOperation.findOne(
				new Query(Criteria.where("username").is("Mirths")), User.class,
				"test");

		System.out.println("savedUser : " + savedUser.getUsername());
		System.out.println("savedUser : " + savedUser.getPassword());
		System.out.println("savedUser : " + savedUser.getValue());
		System.out.println("savedUser : " + savedUser.getAddress().getStreet());
		System.out.println("savedUser : " + savedUser.getAddress().getNumber());
		System.out.println("savedUser : " + savedUser.getAddress().getType());
		
		

//		// update
//		mongoOperation.updateMulti(
//				new Query(Criteria.where("username").is("test")),
//				Update.update("password", "new password"), "users");
//
//		// find
//		User updatedUser = mongoOperation.findOne(
//				new Query(Criteria.where("username").is("test")), User.class,
//				"users");
//
//		System.out.println("updatedUser : " + updatedUser);
//
//		// delete
//		mongoOperation.remove(
//				new Query(Criteria.where("username").is("test")), "users");
//
		// List
		List<User> listUser = mongoOperation.findAll(User.class, "test");
		System.out.println("Number of user = " + listUser.size());

	}
	
	public static User getUser() {
		User user = new User("Mirths", "1234");
		user.setValue(new BigDecimal("6.00"));
		
		Address address = new Address();
		address.setNumber(123);
		address.setStreet("Rua X");
		address.setType(Type.TWO);
		user.setAddress(address);
		
		return user;
		
	}
	
	
	

}
