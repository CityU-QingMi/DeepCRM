	@Test
	public void sendWithReceipt() throws Exception {
		this.session.afterConnected(this.connection);
		assertTrue(this.session.isConnected());

		this.session.setTaskScheduler(mock(TaskScheduler.class));
		this.session.setAutoReceipt(true);
		this.session.send("/topic/foo", "sample payload");

		Message<byte[]> message = this.messageCaptor.getValue();
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		assertNotNull(accessor.getReceipt());

		StompHeaders stompHeaders = new StompHeaders();
		stompHeaders.setDestination("/topic/foo");
		stompHeaders.setReceipt("my-receipt");
		this.session.send(stompHeaders, "sample payload");

		message = this.messageCaptor.getValue();
		accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		assertEquals("my-receipt", accessor.getReceipt());
	}
