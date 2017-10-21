	@Test
	@SuppressWarnings({ "", "" })
	public void receiptNotReceived() throws Exception {
		TaskScheduler taskScheduler = mock(TaskScheduler.class);

		this.session.afterConnected(this.connection);
		this.session.setTaskScheduler(taskScheduler);

		AtomicReference<Boolean> notReceived = new AtomicReference<>();

		ScheduledFuture future = mock(ScheduledFuture.class);
		when(taskScheduler.schedule(any(Runnable.class), any(Date.class))).thenReturn(future);

		StompHeaders headers = new StompHeaders();
		headers.setDestination("/topic/foo");
		headers.setReceipt("my-receipt");
		Receiptable receiptable = this.session.send(headers, "payload");
		receiptable.addReceiptLostTask(() -> notReceived.set(true));

		ArgumentCaptor<Runnable> taskCaptor = ArgumentCaptor.forClass(Runnable.class);
		verify(taskScheduler).schedule(taskCaptor.capture(), (Date) notNull());
		Runnable scheduledTask = taskCaptor.getValue();
		assertNotNull(scheduledTask);

		assertNull(notReceived.get());

		scheduledTask.run();
		assertTrue(notReceived.get());
		verify(future).cancel(true);
		verifyNoMoreInteractions(future);
	}
