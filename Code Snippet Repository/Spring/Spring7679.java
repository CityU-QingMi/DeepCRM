	@SuppressWarnings("")
	@Test
	public void brokerUnavailableEvent() throws Exception {

		ScheduledFuture future = Mockito.mock(ScheduledFuture.class);
		when(this.taskScheduler.scheduleWithFixedDelay(any(Runnable.class), any(Long.class))).thenReturn(future);

		BrokerAvailabilityEvent event = new BrokerAvailabilityEvent(true, this);
		this.handler.onApplicationEvent(event);
		verifyNoMoreInteractions(future);

		event = new BrokerAvailabilityEvent(false, this);
		this.handler.onApplicationEvent(event);
		verify(future).cancel(true);
	}
