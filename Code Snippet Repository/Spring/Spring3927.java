	@Test
	public void testDaylightSavingMissingHour() throws Exception {
		// This trigger has to be somewhere in between 2am and 3am
		CronTrigger trigger = new CronTrigger("0 10 2 * * *", timeZone);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		calendar.set(Calendar.MONTH, Calendar.MARCH);
		calendar.set(Calendar.YEAR, 2013);
		calendar.set(Calendar.HOUR_OF_DAY, 1);
		calendar.set(Calendar.SECOND, 54);
		Date date = calendar.getTime();
		TriggerContext context1 = getTriggerContext(date);
		if (timeZone.equals(TimeZone.getTimeZone("CET"))) {
			// Clocks go forward an hour so 2am doesn't exist in CET for this date
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		calendar.set(Calendar.MINUTE, 10);
		calendar.set(Calendar.SECOND, 0);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context1));
	}
