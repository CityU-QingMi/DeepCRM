	@Test
	public void scheduleAndCancelHeartbeat() throws Exception {
		ScheduledFuture<?> task = mock(ScheduledFuture.class);
		willReturn(task).given(this.taskScheduler).schedule(any(Runnable.class), any(Date.class));

		this.session.setActive(true);
		this.session.scheduleHeartbeat();

		verify(this.taskScheduler).schedule(any(Runnable.class), any(Date.class));
		verifyNoMoreInteractions(this.taskScheduler);

		given(task.isCancelled()).willReturn(false);
		given(task.cancel(false)).willReturn(true);

		this.session.cancelHeartbeat();

		verify(task).cancel(false);
		verifyNoMoreInteractions(task);
	}
