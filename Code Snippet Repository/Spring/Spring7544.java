	@Test
	public void readWriteIntervalCalculation() throws Exception {

		this.messageHandler.setHeartbeatValue(new long[] {1, 1});
		this.messageHandler.setTaskScheduler(this.taskScheduler);
		this.messageHandler.start();

		ArgumentCaptor<Runnable> taskCaptor = ArgumentCaptor.forClass(Runnable.class);
		verify(this.taskScheduler).scheduleWithFixedDelay(taskCaptor.capture(), eq(1L));
		Runnable heartbeatTask = taskCaptor.getValue();
		assertNotNull(heartbeatTask);

		String id = "sess1";
		TestPrincipal user = new TestPrincipal("joe");
		Message<String> connectMessage = createConnectMessage(id, user, new long[] {10000, 10000});
		this.messageHandler.handleMessage(connectMessage);

		Thread.sleep(10);
		heartbeatTask.run();

		verify(this.clientOutboundChannel, times(1)).send(this.messageCaptor.capture());
		List<Message<?>> messages = this.messageCaptor.getAllValues();
		assertEquals(1, messages.size());
		assertEquals(SimpMessageType.CONNECT_ACK,
				messages.get(0).getHeaders().get(SimpMessageHeaderAccessor.MESSAGE_TYPE_HEADER));
	}
