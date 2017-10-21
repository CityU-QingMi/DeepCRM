	@Test
	public void fixedIncrease() {
		ExponentialBackOff backOff = new ExponentialBackOff(100L, 1.0);
		backOff.setMaxElapsedTime(300l);

		BackOffExecution execution = backOff.start();
		assertEquals(100l, execution.nextBackOff());
		assertEquals(100l, execution.nextBackOff());
		assertEquals(100l, execution.nextBackOff());
		assertEquals(BackOffExecution.STOP, execution.nextBackOff());
	}
