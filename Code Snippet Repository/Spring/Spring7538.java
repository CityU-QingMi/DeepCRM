	@Test
	public void connect() {

		this.messageHandler.start();

		String id = "sess1";
		Message<String> connectMessage = createConnectMessage(id, new TestPrincipal("joe"), null);
		this.messageHandler.setTaskScheduler(this.taskScheduler);
		this.messageHandler.handleMessage(connectMessage);

		verify(this.clientOutboundChannel, times(1)).send(this.messageCaptor.capture());
		Message<?> connectAckMessage = this.messageCaptor.getValue();

		SimpMessageHeaderAccessor connectAckHeaders = SimpMessageHeaderAccessor.wrap(connectAckMessage);
		assertEquals(connectMessage, connectAckHeaders.getHeader(SimpMessageHeaderAccessor.CONNECT_MESSAGE_HEADER));
		assertEquals(id, connectAckHeaders.getSessionId());
		assertEquals("joe", connectAckHeaders.getUser().getName());
		assertArrayEquals(new long[] {10000, 10000},
				SimpMessageHeaderAccessor.getHeartbeat(connectAckHeaders.getMessageHeaders()));
	}
