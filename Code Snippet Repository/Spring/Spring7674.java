	@Test
	public void handleMessageFromBrokerWithActiveSession() {
		TestSimpUser simpUser = new TestSimpUser("joe");
		simpUser.addSessions(new TestSimpSession("123"));
		when(this.registry.getUser("joe")).thenReturn(simpUser);

		this.handler.setBroadcastDestination("/topic/unresolved");
		given(this.brokerChannel.send(Mockito.any(Message.class))).willReturn(true);

		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.MESSAGE);
		accessor.setSessionId("system123");
		accessor.setDestination("/topic/unresolved");
		accessor.setNativeHeader(ORIGINAL_DESTINATION, "/user/joe/queue/foo");
		accessor.setNativeHeader("customHeader", "customHeaderValue");
		accessor.setLeaveMutable(true);
		byte[] payload = "payload".getBytes(StandardCharsets.UTF_8);
		this.handler.handleMessage(MessageBuilder.createMessage(payload, accessor.getMessageHeaders()));

		ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
		Mockito.verify(this.brokerChannel).send(captor.capture());
		assertNotNull(captor.getValue());
		SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(captor.getValue());
		assertEquals("/queue/foo-user123", headers.getDestination());
		assertEquals("/user/queue/foo", headers.getFirstNativeHeader(ORIGINAL_DESTINATION));
		assertEquals("customHeaderValue", headers.getFirstNativeHeader("customHeader"));
		assertArrayEquals(payload, (byte[]) captor.getValue().getPayload());
	}
