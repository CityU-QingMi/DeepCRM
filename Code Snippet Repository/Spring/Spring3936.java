	@Test
	public void fixedDelayWithTimeUnitAndInitialDelayFirstExecution() {
		Date now = new Date();
		long period = 5;
		long initialDelay = 30;
		PeriodicTrigger trigger = new PeriodicTrigger(period, TimeUnit.SECONDS);
		trigger.setInitialDelay(initialDelay);
		Date next = trigger.nextExecutionTime(context(null, null, null));
		assertApproximateDifference(now, next, initialDelay * 1000);
	}
