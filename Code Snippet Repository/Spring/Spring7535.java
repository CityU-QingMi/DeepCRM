	@Test
	public void subcribePublish() {

		this.messageHandler.start();

		this.messageHandler.handleMessage(createSubscriptionMessage("sess1", "sub1", "/foo"));
		this.messageHandler.handleMessage(createSubscriptionMessage("sess1", "sub2", "/foo"));
		this.messageHandler.handleMessage(createSubscriptionMessage("sess1", "sub3", "/bar"));

		this.messageHandler.handleMessage(createSubscriptionMessage("sess2", "sub1", "/foo"));
		this.messageHandler.handleMessage(createSubscriptionMessage("sess2", "sub2", "/foo"));
		this.messageHandler.handleMessage(createSubscriptionMessage("sess2", "sub3", "/bar"));

		this.messageHandler.handleMessage(createMessage("/foo", "message1"));
		this.messageHandler.handleMessage(createMessage("/bar", "message2"));

		verify(this.clientOutboundChannel, times(6)).send(this.messageCaptor.capture());
		assertTrue(messageCaptured("sess1", "sub1", "/foo"));
		assertTrue(messageCaptured("sess1", "sub2", "/foo"));
		assertTrue(messageCaptured("sess2", "sub1", "/foo"));
		assertTrue(messageCaptured("sess2", "sub2", "/foo"));
		assertTrue(messageCaptured("sess1", "sub3", "/bar"));
		assertTrue(messageCaptured("sess2", "sub3", "/bar"));
	}
