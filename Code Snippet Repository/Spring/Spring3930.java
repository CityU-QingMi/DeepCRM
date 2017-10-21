	@Test
	public void testIncrementHourAndRollover() throws Exception {
		CronTrigger trigger = new CronTrigger("0 0 * * * *", timeZone);
		calendar.set(Calendar.MONTH, 9);
		calendar.set(Calendar.DAY_OF_MONTH, 10);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 1);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime();
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 11);
		TriggerContext context1 = getTriggerContext(date);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context1));
		calendar.set(Calendar.HOUR_OF_DAY, 1);
		TriggerContext context2 = getTriggerContext(date);
		assertEquals(calendar.getTime(), trigger.nextExecutionTime(context2));
	}
