	@Test
	public void handleConnectedFrame() throws Exception {
		this.session.afterConnected(this.connection);
		assertTrue(this.session.isConnected());

		this.connectHeaders.setHeartbeat(new long[] {10000, 10000});

		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.CONNECTED);
		accessor.setVersion("1.2");
		accessor.setHeartbeat(10000, 10000);
		accessor.setLeaveMutable(true);
		this.session.handleMessage(MessageBuilder.createMessage(new byte[0], accessor.getMessageHeaders()));

		StompHeaders stompHeaders = StompHeaders.readOnlyStompHeaders(accessor.getNativeHeaders());
		verify(this.sessionHandler).afterConnected(this.session, stompHeaders);
		verifyNoMoreInteractions(this.sessionHandler);
	}
