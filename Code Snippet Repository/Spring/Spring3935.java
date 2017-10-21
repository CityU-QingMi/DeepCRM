	@Test
	public void fixedDelayWithInitialDelayFirstExecution() {
		Date now = new Date();
		long period = 5000;
		long initialDelay = 30000;
		PeriodicTrigger trigger = new PeriodicTrigger(period);
		trigger.setInitialDelay(initialDelay);
		Date next = trigger.nextExecutionTime(context(null, null, null));
		assertApproximateDifference(now, next, initialDelay);
	}
