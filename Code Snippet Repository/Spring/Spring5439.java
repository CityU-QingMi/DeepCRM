	@Test
	public void toStringContent() {
		FixedBackOff backOff = new FixedBackOff(200L, 10);
		BackOffExecution execution = backOff.start();
		assertEquals("FixedBackOff{interval=200, currentAttempts=0, maxAttempts=10}", execution.toString());
		execution.nextBackOff();
		assertEquals("FixedBackOff{interval=200, currentAttempts=1, maxAttempts=10}", execution.toString());
		execution.nextBackOff();
		assertEquals("FixedBackOff{interval=200, currentAttempts=2, maxAttempts=10}", execution.toString());
	}
