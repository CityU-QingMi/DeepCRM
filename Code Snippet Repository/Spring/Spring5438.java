	@Test
	public void liveUpdate() {
		FixedBackOff backOff = new FixedBackOff(100L, 1);
		BackOffExecution execution = backOff.start();
		assertEquals(100l, execution.nextBackOff());

		backOff.setInterval(200l);
		backOff.setMaxAttempts(2);

		assertEquals(200l, execution.nextBackOff());
		assertEquals(BackOffExecution.STOP, execution.nextBackOff());
	}
