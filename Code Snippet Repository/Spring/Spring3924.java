	@Test
	public void testLeapYearSpecificDate() throws Exception {
		CronTrigger trigger = new CronTrigger("0 0 0 29 2 *", timeZone);
		calendar.set(Calendar.YEAR, 2007);
		calendar.set(Calendar.DAY_OF_MONTH, 10);
		calendar.set(Calendar.MONTH, 1); // 2=February
		Date date = calendar.getTime();
		TriggerContext context1 = getTriggerContext(date);
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.DAY_OF_MONTH, 29);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context1));
		calendar.add(Calendar.YEAR, 4);
		TriggerContext context2 = getTriggerContext(date);
		assertEquals(calendar.getTime(), date = trigger.nextExecutionTime(context2));
	}
