	@Test
	public void testSpecificMinuteSecond() throws Exception {
		CronTrigger trigger = new CronTrigger("55 5 * * * *", timeZone);
		calendar.set(Calendar.MINUTE, 4);
		calendar.set(Calendar.SECOND, 54);
		Date date = calendar.getTime();
		TriggerContext context1 = getTriggerContext(date);
		calendar.add(Calendar.MINUTE, 1);
		calendar.set(Calendar.SECOND, 55);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context1));
		calendar.add(Calendar.HOUR, 1);
		TriggerContext context2 = getTriggerContext(date);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context2));
	}
