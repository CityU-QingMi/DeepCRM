	@Test
	public void handleMessageToClientWithHeartbeatSuppressingSockJsHeartbeat() throws IOException {

		SockJsSession sockJsSession = Mockito.mock(SockJsSession.class);
		when(sockJsSession.getId()).thenReturn("s1");
		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.CONNECTED);
		accessor.setHeartbeat(0, 10);
		Message<byte[]> message = MessageBuilder.createMessage(EMPTY_PAYLOAD, accessor.getMessageHeaders());
		this.protocolHandler.handleMessageToClient(sockJsSession, message);

		verify(sockJsSession).getId();
		verify(sockJsSession).getPrincipal();
		verify(sockJsSession).disableHeartbeat();
		verify(sockJsSession).sendMessage(any(WebSocketMessage.class));
		verifyNoMoreInteractions(sockJsSession);

		sockJsSession = Mockito.mock(SockJsSession.class);
		when(sockJsSession.getId()).thenReturn("s1");
		accessor = StompHeaderAccessor.create(StompCommand.CONNECTED);
		accessor.setHeartbeat(0, 0);
		message = MessageBuilder.createMessage(EMPTY_PAYLOAD, accessor.getMessageHeaders());
		this.protocolHandler.handleMessageToClient(sockJsSession, message);

		verify(sockJsSession).getId();
		verify(sockJsSession).getPrincipal();
		verify(sockJsSession).sendMessage(any(WebSocketMessage.class));
		verifyNoMoreInteractions(sockJsSession);
	}
