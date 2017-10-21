	@Test
	public void heartbeatNotSupportedByServer() throws Exception {
		this.session.afterConnected(this.connection);
		verify(this.connection).send(any());

		this.connectHeaders.setHeartbeat(new long[] {10000, 10000});

		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.CONNECTED);
		accessor.setVersion("1.2");
		accessor.setHeartbeat(0, 0);
		accessor.setLeaveMutable(true);
		this.session.handleMessage(MessageBuilder.createMessage(new byte[0], accessor.getMessageHeaders()));

		verifyNoMoreInteractions(this.connection);
	}
