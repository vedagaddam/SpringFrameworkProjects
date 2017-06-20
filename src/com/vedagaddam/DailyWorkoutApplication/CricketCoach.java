package com.vedagaddam.DailyWorkoutApplication;

public class CricketCoach implements Coach {
	
	//creating the private fields
	private FortuneService fortuneService;
	private String emailAddress;
	private String team;
	
	public CricketCoach(){
		System.out.println("Cricket Coach constructor");
	}
	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Bat for 10 minutes";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return "You will score more runs"+ fortuneService.getFortune();
	}
	
	//creating the setter methods
	public void setFortuneService(FortuneService fortuneService){
		System.out.println("Cricket Coach Fortune setter method");
		this.fortuneService = fortuneService;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		System.out.println("Cricket Coach emailAddress setter method");
		this.emailAddress = emailAddress;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		System.out.println("Cricket Coach team setter method");
		this.team = team;
	}
	

}
