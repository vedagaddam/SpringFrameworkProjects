package com.vedagaddam.DailyWorkoutApplication;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WorkoutAppWithSetterInjection {

	public static void main(String args[]){
		
		//Load the spring configuration file; Create a Spring Container using ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = 
						new ClassPathXmlApplicationContext("applicationContext.xml");
		//retrieve the bean from the spring container
		CricketCoach theCoach = context.getBean("myCricketCoach", CricketCoach.class);
		
		//call the methods
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		System.out.println(theCoach.getEmailAddress());
		System.out.println(theCoach.getTeam());
		//Close the context
		context.close();
	}
}
