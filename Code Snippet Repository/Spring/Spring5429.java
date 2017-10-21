	@Test
	public void toStringContent() {
		ExponentialBackOff backOff = new ExponentialBackOff(2000L, 2.0);
		BackOffExecution execution = backOff.start();
		assertEquals("ExponentialBackOff{currentInterval=n/a, multiplier=2.0}", execution.toString());
		execution.nextBackOff();
		assertEquals("ExponentialBackOff{currentInterval=2000ms, multiplier=2.0}", execution.toString());
		execution.nextBackOff();
		assertEquals("ExponentialBackOff{currentInterval=4000ms, multiplier=2.0}", execution.toString());
	}
