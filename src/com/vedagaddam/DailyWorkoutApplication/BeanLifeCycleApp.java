package com.vedagaddam.DailyWorkoutApplication;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleApp {

	public static void main(String[] args) {
		
		//Load the spring configuration file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("beanLifeCycleApplicationContext.xml");
		
		//retrieve the beans
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		//Calling the methods
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		
		context.close();
	}

}
