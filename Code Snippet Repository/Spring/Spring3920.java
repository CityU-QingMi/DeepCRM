	@Test
	public void testSpecificMinuteHour() throws Exception {
		CronTrigger trigger = new CronTrigger("* 5 10 * * *", timeZone);
		calendar.set(Calendar.MINUTE, 4);
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		Date date = calendar.getTime();
		calendar.add(Calendar.MINUTE, 1);
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		calendar.set(Calendar.SECOND, 0);
		TriggerContext context1 = getTriggerContext(date);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context1));
		// next trigger is in one second because second is wildcard
		calendar.add(Calendar.SECOND, 1);
		TriggerContext context2 = getTriggerContext(date);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context2));
	}
