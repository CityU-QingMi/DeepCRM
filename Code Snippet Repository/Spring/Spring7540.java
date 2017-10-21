	@SuppressWarnings("")
	@Test
	public void startAndStopWithHeartbeatValue() throws Exception {

		ScheduledFuture future = mock(ScheduledFuture.class);
		when(this.taskScheduler.scheduleWithFixedDelay(any(Runnable.class), eq(15000L))).thenReturn(future);

		this.messageHandler.setTaskScheduler(this.taskScheduler);
		this.messageHandler.setHeartbeatValue(new long[] {15000, 16000});
		this.messageHandler.start();

		verify(this.taskScheduler).scheduleWithFixedDelay(any(Runnable.class), eq(15000L));
		verifyNoMoreInteractions(this.taskScheduler, future);

		this.messageHandler.stop();

		verify(future).cancel(true);
		verifyNoMoreInteractions(future);
	}
