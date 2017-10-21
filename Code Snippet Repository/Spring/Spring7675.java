	@Test
	public void handleMessageFromBrokerWithoutActiveSession() {
		this.handler.setBroadcastDestination("/topic/unresolved");
		given(this.brokerChannel.send(Mockito.any(Message.class))).willReturn(true);

		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.MESSAGE);
		accessor.setSessionId("system123");
		accessor.setDestination("/topic/unresolved");
		accessor.setNativeHeader(ORIGINAL_DESTINATION, "/user/joe/queue/foo");
		accessor.setLeaveMutable(true);
		byte[] payload = "payload".getBytes(StandardCharsets.UTF_8);
		this.handler.handleMessage(MessageBuilder.createMessage(payload, accessor.getMessageHeaders()));

		// No re-broadcast
		verifyNoMoreInteractions(this.brokerChannel);
	}
