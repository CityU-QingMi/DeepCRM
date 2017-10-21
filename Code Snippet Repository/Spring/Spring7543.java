	@Test
	public void writeInactivity() throws Exception {

		this.messageHandler.setHeartbeatValue(new long[] {1, 0});
		this.messageHandler.setTaskScheduler(this.taskScheduler);
		this.messageHandler.start();

		ArgumentCaptor<Runnable> taskCaptor = ArgumentCaptor.forClass(Runnable.class);
		verify(this.taskScheduler).scheduleWithFixedDelay(taskCaptor.capture(), eq(1L));
		Runnable heartbeatTask = taskCaptor.getValue();
		assertNotNull(heartbeatTask);

		String id = "sess1";
		TestPrincipal user = new TestPrincipal("joe");
		Message<String> connectMessage = createConnectMessage(id, user, new long[] {0, 1});
		this.messageHandler.handleMessage(connectMessage);

		Thread.sleep(10);
		heartbeatTask.run();

		verify(this.clientOutboundChannel, times(2)).send(this.messageCaptor.capture());
		List<Message<?>> messages = this.messageCaptor.getAllValues();
		assertEquals(2, messages.size());

		MessageHeaders headers = messages.get(0).getHeaders();
		assertEquals(SimpMessageType.CONNECT_ACK, headers.get(SimpMessageHeaderAccessor.MESSAGE_TYPE_HEADER));
		headers = messages.get(1).getHeaders();
		assertEquals(SimpMessageType.HEARTBEAT, headers.get(SimpMessageHeaderAccessor.MESSAGE_TYPE_HEADER));
		assertEquals(id, headers.get(SimpMessageHeaderAccessor.SESSION_ID_HEADER));
		assertEquals(user, headers.get(SimpMessageHeaderAccessor.USER_HEADER));
	}
