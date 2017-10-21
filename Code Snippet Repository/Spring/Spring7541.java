	@SuppressWarnings("")
	@Test
	public void startWithOneZeroHeartbeatValue() throws Exception {

		this.messageHandler.setTaskScheduler(this.taskScheduler);
		this.messageHandler.setHeartbeatValue(new long[] {0, 10000});
		this.messageHandler.start();

		verify(this.taskScheduler).scheduleWithFixedDelay(any(Runnable.class), eq(10000L));
	}
