package com.vedagaddam.springsportsworkout;

public class MyWorkoutApp {

	public static void main(String[] args) {
		
		Coach theCoach = new TrackCoach();
		System.out.println(theCoach.getDailyWorkout());

	}

}
