package com.vedagaddam.DailyWorkoutApplication;

import java.util.Random;

public class HappyFortuneSevice implements FortuneService {

	@Override
	public String getFortune() {
		String[] randomFortune = {"Today is your lucky day", "Today is a bad day", "You make your own luck"};
		Random random = new Random();
		int index = random.nextInt(randomFortune.length);
		return randomFortune[index];
	}

}
