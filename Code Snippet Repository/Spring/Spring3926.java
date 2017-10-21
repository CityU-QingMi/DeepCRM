	@Test
	public void testMonthSequence() throws Exception {
		CronTrigger trigger = new CronTrigger("0 30 23 30 1/3 ?", timeZone);
		calendar.set(2010, 11, 30);
		Date date = calendar.getTime();
		// set expected next trigger time
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.MONTH, 1);
		TriggerContext context1 = getTriggerContext(date);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context1));
		// Next trigger is 3 months latter
		calendar.add(Calendar.MONTH, 3);
		TriggerContext context2 = getTriggerContext(date);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context2));
		// Next trigger is 3 months latter
		calendar.add(Calendar.MONTH, 3);
		TriggerContext context3 = getTriggerContext(date);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context3));
	}
