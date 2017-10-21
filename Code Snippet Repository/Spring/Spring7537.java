	@Test
	public void subcribeDisconnectPublish() {

		String sess1 = "sess1";
		String sess2 = "sess2";

		this.messageHandler.start();

		this.messageHandler.handleMessage(createSubscriptionMessage(sess1, "sub1", "/foo"));
		this.messageHandler.handleMessage(createSubscriptionMessage(sess1, "sub2", "/foo"));
		this.messageHandler.handleMessage(createSubscriptionMessage(sess1, "sub3", "/bar"));

		this.messageHandler.handleMessage(createSubscriptionMessage(sess2, "sub1", "/foo"));
		this.messageHandler.handleMessage(createSubscriptionMessage(sess2, "sub2", "/foo"));
		this.messageHandler.handleMessage(createSubscriptionMessage(sess2, "sub3", "/bar"));

		SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.create(SimpMessageType.DISCONNECT);
		headers.setSessionId(sess1);
		headers.setUser(new TestPrincipal("joe"));
		Message<byte[]> message = MessageBuilder.createMessage(new byte[0], headers.getMessageHeaders());
		this.messageHandler.handleMessage(message);

		this.messageHandler.handleMessage(createMessage("/foo", "message1"));
		this.messageHandler.handleMessage(createMessage("/bar", "message2"));

		verify(this.clientOutboundChannel, times(4)).send(this.messageCaptor.capture());

		Message<?> captured = this.messageCaptor.getAllValues().get(0);
		assertEquals(SimpMessageType.DISCONNECT_ACK, SimpMessageHeaderAccessor.getMessageType(captured.getHeaders()));
		assertSame(message, captured.getHeaders().get(SimpMessageHeaderAccessor.DISCONNECT_MESSAGE_HEADER));
		assertEquals(sess1, SimpMessageHeaderAccessor.getSessionId(captured.getHeaders()));
		assertEquals("joe", SimpMessageHeaderAccessor.getUser(captured.getHeaders()).getName());

		assertTrue(messageCaptured(sess2, "sub1", "/foo"));
		assertTrue(messageCaptured(sess2, "sub2", "/foo"));
		assertTrue(messageCaptured(sess2, "sub3", "/bar"));
	}
