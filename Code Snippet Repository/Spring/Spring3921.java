	@Test
	public void testSpecificDayOfMonthSecond() throws Exception {
		CronTrigger trigger = new CronTrigger("55 * * 3 * *", timeZone);
		calendar.set(Calendar.DAY_OF_MONTH, 2);
		calendar.set(Calendar.SECOND, 54);
		Date date = calendar.getTime();
		TriggerContext context1 = getTriggerContext(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 55);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context1));
		calendar.add(Calendar.MINUTE, 1);
		TriggerContext context2 = getTriggerContext(date);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context2));
	}
