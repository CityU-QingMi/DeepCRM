	public void testAtHourAndMinuteOnGivenDaysOfWeek() {
		
		CronTrigger trigger = newTrigger().withIdentity("test")
				.withSchedule(
					atHourAndMinuteOnGivenDaysOfWeek(10, 0, DateBuilder.MONDAY, DateBuilder.THURSDAY, DateBuilder.FRIDAY))
				.build();
		Assert.assertEquals("0 0 10 ? * 2,5,6", trigger.getCronExpression());

		trigger = newTrigger().withIdentity("test")
			.withSchedule(
			atHourAndMinuteOnGivenDaysOfWeek(10, 0, DateBuilder.WEDNESDAY))
			.build();
		Assert.assertEquals("0 0 10 ? * 4", trigger.getCronExpression());
	}
