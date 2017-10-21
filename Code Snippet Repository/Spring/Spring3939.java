	@Test
	public void fixedRateWithTimeUnitAndInitialDelayFirstExecution() {
		Date now = new Date();
		long period = 5;
		long initialDelay = 30;
		PeriodicTrigger trigger = new PeriodicTrigger(period, TimeUnit.MINUTES);
		trigger.setFixedRate(true);
		trigger.setInitialDelay(initialDelay);
		Date next = trigger.nextExecutionTime(context(null, null, null));
		assertApproximateDifference(now, next, (initialDelay * 60 * 1000));
	}
