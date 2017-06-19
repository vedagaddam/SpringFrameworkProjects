package com.vedagaddam.DailyWorkoutApplication;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WorkoutAppWithSpring {

	public static void main(String args[]){
		
		//Create a Spring Container using ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//Retrieve Beans from the container; getBean has 2 parameters 1. Bean name 2. The interface class name
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		//Call the method on the bean
		System.out.println(theCoach.getDailyWorkout());
	}
}
