package com.vedagaddam.DailyWorkoutApplication;

public class TrackCoach implements Coach {

	//Define a private field for dependency
	private FortuneService fortuneService;
	//Define a constructor for dependency injection
	public TrackCoach(FortuneService fortuneService){
		this.fortuneService = fortuneService;
		}
	public TrackCoach(){
		
	}
	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Run for 20 minutes";
	}

	@Override
	public String getDailyFortune() {
		//Use fortuneservice to get the fortune
		return "Just do it " +fortuneService.getFortune();
	}

}
