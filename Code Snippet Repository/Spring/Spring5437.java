	@Test
	public void startReturnDifferentInstances() {
		FixedBackOff backOff = new FixedBackOff(100L, 1);
		BackOffExecution execution = backOff.start();
		BackOffExecution execution2 = backOff.start();

		assertEquals(100l, execution.nextBackOff());
		assertEquals(100l, execution2.nextBackOff());
		assertEquals(BackOffExecution.STOP, execution.nextBackOff());
		assertEquals(BackOffExecution.STOP, execution2.nextBackOff());
	}
