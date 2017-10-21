	@Test
	public void startReturnDifferentInstances() {
		ExponentialBackOff backOff = new ExponentialBackOff();
		backOff.setInitialInterval(2000L);
		backOff.setMultiplier(2.0);
		backOff.setMaxElapsedTime(4000L);

		BackOffExecution execution = backOff.start();
		BackOffExecution execution2 = backOff.start();

		assertEquals(2000l, execution.nextBackOff());
		assertEquals(2000l, execution2.nextBackOff());
		assertEquals(4000l, execution.nextBackOff());
		assertEquals(4000l, execution2.nextBackOff());
		assertEquals(BackOffExecution.STOP, execution.nextBackOff());
		assertEquals(BackOffExecution.STOP, execution2.nextBackOff());
	}
