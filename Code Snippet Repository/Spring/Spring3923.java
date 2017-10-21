	@Test(expected = IllegalArgumentException.class)
	public void testNonExistentSpecificDate() throws Exception {
		// TODO: maybe try and detect this as a special case in parser?
		CronTrigger trigger = new CronTrigger("0 0 0 31 6 *", timeZone);
		calendar.set(Calendar.DAY_OF_MONTH, 10);
		calendar.set(Calendar.MONTH, 2);
		Date date = calendar.getTime();
		TriggerContext context1 = getTriggerContext(date);
		trigger.nextExecutionTime(context1);
		// new CronTrigger("0 0 0 30 1 ?", timeZone);
	}
