package com.vedagaddam.DailyWorkoutApplication;

public class BaseballCoach  implements Coach{

	//Define a private field for dependency
	private FortuneService fortuneService;
	//Define a constructor for dependency injection
	public BaseballCoach(FortuneService fortuneService){
		this.fortuneService = fortuneService;
	}
	public BaseballCoach(){
		
	}
	public String getDailyWorkout(){
		return "Workout for 30 minutes";
	}

	@Override
	public String getDailyFortune() {
		
		//Use fortuneservice to get the fortune
		return fortuneService.getFortune();
	}
	
}
