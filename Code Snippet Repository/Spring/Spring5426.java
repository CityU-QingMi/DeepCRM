	@Test
	public void maxIntervalReached() {
		ExponentialBackOff backOff = new ExponentialBackOff(2000L, 2.0);
		backOff.setMaxInterval(4000L);

		BackOffExecution execution = backOff.start();
		assertEquals(2000l, execution.nextBackOff());
		assertEquals(4000l, execution.nextBackOff());
		assertEquals(4000l, execution.nextBackOff()); // max reached
		assertEquals(4000l, execution.nextBackOff());
	}
