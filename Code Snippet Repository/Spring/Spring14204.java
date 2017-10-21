	@Test
	public void fallbackAfterTimeout() throws Exception {
		TaskScheduler scheduler = mock(TaskScheduler.class);
		Runnable sessionCleanupTask = mock(Runnable.class);
		DefaultTransportRequest request1 = createTransportRequest(this.webSocketTransport, TransportType.WEBSOCKET);
		DefaultTransportRequest request2 = createTransportRequest(this.xhrTransport, TransportType.XHR_STREAMING);
		request1.setFallbackRequest(request2);
		request1.setTimeoutScheduler(scheduler);
		request1.addTimeoutTask(sessionCleanupTask);
		request1.connect(null, this.connectFuture);

		assertTrue(this.webSocketTransport.invoked());
		assertFalse(this.xhrTransport.invoked());

		// Get and invoke the scheduled timeout task
		ArgumentCaptor<Runnable> taskCaptor = ArgumentCaptor.forClass(Runnable.class);
		verify(scheduler).schedule(taskCaptor.capture(), any(Date.class));
		verifyNoMoreInteractions(scheduler);
		taskCaptor.getValue().run();

		assertTrue(this.xhrTransport.invoked());
		verify(sessionCleanupTask).run();
	}
