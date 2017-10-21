	@Test
	public void handleMessageWithoutActiveSession() {
		this.handler.setBroadcastDestination("/topic/unresolved");
		given(this.brokerChannel.send(Mockito.any(Message.class))).willReturn(true);
		this.handler.handleMessage(createWith(SimpMessageType.MESSAGE, "joe", "123", "/user/joe/queue/foo"));

		ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
		Mockito.verify(this.brokerChannel).send(captor.capture());

		Message message = captor.getValue();
		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(message);
		assertEquals("/topic/unresolved", accessor.getDestination());
		assertEquals("/user/joe/queue/foo", accessor.getFirstNativeHeader(ORIGINAL_DESTINATION));

		// Should ignore our own broadcast to brokerChannel

		this.handler.handleMessage(message);
		Mockito.verifyNoMoreInteractions(this.brokerChannel);
	}
