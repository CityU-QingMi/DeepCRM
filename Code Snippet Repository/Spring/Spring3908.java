	@Test
	public void testIncrementSecondWithPreviousExecutionTooEarly() throws Exception {
		CronTrigger trigger = new CronTrigger("11 * * * * *", timeZone);
		calendar.set(Calendar.SECOND, 11);
		SimpleTriggerContext context = new SimpleTriggerContext();
		context.update(calendar.getTime(), new Date(calendar.getTimeInMillis() - 100),
				new Date(calendar.getTimeInMillis() - 90));
		calendar.add(Calendar.MINUTE, 1);
		assertEquals(calendar.getTime(), trigger.nextExecutionTime(context));
	}
