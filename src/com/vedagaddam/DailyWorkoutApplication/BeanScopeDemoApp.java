package com.vedagaddam.DailyWorkoutApplication;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		
		//Load the spring configuration file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("beanScopeApplicationContext.xml");
		
		//retrieve the beans
		Coach theCoach = context.getBean("myCoach", Coach.class);
		Coach alphaCoach = context.getBean("myCoach", Coach.class);
		
		boolean res = (theCoach == alphaCoach);
		System.out.println("Singleton check: Pointing to same location - "+ res);
		System.out.println("Memory location of theCoach "+ theCoach);
		System.out.println("Memory location of alphaCoach "+ alphaCoach);
		/*Singleton scope creates only one object and is shared across all classes
		 * 	Singleton check: Pointing to same location - true
			Memory location of theCoach com.vedagaddam.DailyWorkoutApplication.TrackCoach@5cb9f472
			Memory location of alphaCoach com.vedagaddam.DailyWorkoutApplication.TrackCoach@5cb9f472
		 */
		/*
		 * Prototype scope: new object is created for every request
		 *  Singleton check: Pointing to same location - false
			Memory location of theCoach com.vedagaddam.DailyWorkoutApplication.TrackCoach@4566e5bd
			Memory location of alphaCoach com.vedagaddam.DailyWorkoutApplication.TrackCoach@1ed4004b
		 */
		context.close();
	}

}
