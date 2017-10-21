	private void testInactivityTaskScheduling(Runnable runnable, long delay, long sleepTime)
			throws InterruptedException {

		ArgumentCaptor<Runnable> inactivityTaskCaptor = ArgumentCaptor.forClass(Runnable.class);
		verify(this.taskScheduler).scheduleWithFixedDelay(inactivityTaskCaptor.capture(), eq(delay/2));
		verifyNoMoreInteractions(this.taskScheduler);

		if (sleepTime > 0) {
			Thread.sleep(sleepTime);
		}

		Runnable inactivityTask = inactivityTaskCaptor.getValue();
		assertNotNull(inactivityTask);
		inactivityTask.run();

		if (sleepTime > 0) {
			verify(runnable).run();
		}
		else {
			verifyNoMoreInteractions(runnable);
		}
	}
