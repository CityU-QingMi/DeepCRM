	@Test
	public void fixedRateWithInitialDelayFirstExecution() {
		Date now = new Date();
		long period = 5000;
		long initialDelay = 30000;
		PeriodicTrigger trigger = new PeriodicTrigger(period);
		trigger.setFixedRate(true);
		trigger.setInitialDelay(initialDelay);
		Date next = trigger.nextExecutionTime(context(null, null, null));
		assertApproximateDifference(now, next, initialDelay);
	}
