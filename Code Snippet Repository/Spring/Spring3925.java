	@Test
	public void testWeekDaySequence() throws Exception {
		CronTrigger trigger = new CronTrigger("0 0 7 ? * MON-FRI", timeZone);
		// This is a Saturday
		calendar.set(2009, 8, 26);
		Date date = calendar.getTime();
		// 7 am is the trigger time
		calendar.set(Calendar.HOUR_OF_DAY, 7);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		// Add two days because we start on Saturday
		calendar.add(Calendar.DAY_OF_MONTH, 2);
		TriggerContext context1 = getTriggerContext(date);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context1));
		// Next day is a week day so add one
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		TriggerContext context2 = getTriggerContext(date);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context2));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		TriggerContext context3 = getTriggerContext(date);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context3));
	}
