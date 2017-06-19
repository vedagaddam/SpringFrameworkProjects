package com.vedagaddam.DailyWorkoutApplication;

public class WorkoutAppWithoutSpring {

	public static void main(String[] args) {
		
		Coach theCoach = new TrackCoach();
		System.out.println(theCoach.getDailyWorkout());

	}

}
