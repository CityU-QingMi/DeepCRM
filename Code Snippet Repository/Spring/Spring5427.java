	@Test
	public void maxAttemptsReached() {
		ExponentialBackOff backOff = new ExponentialBackOff(2000L, 2.0);
		backOff.setMaxElapsedTime(4000L);

		BackOffExecution execution = backOff.start();
		assertEquals(2000l, execution.nextBackOff());
		assertEquals(4000l, execution.nextBackOff());
		assertEquals(BackOffExecution.STOP, execution.nextBackOff()); // > 4 sec wait in total
	}
