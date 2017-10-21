	@Test
	public void sendAfterBrokerUnavailable() throws Exception {

		this.brokerRelay.start();
		assertEquals(1, this.brokerRelay.getConnectionCount());

		this.brokerRelay.handleMessage(connectMessage("sess1", "joe"));
		assertEquals(2, this.brokerRelay.getConnectionCount());

		this.brokerRelay.stopInternal();
		this.brokerRelay.handleMessage(message(StompCommand.SEND, "sess1", "joe", "/foo"));
		assertEquals(1, this.brokerRelay.getConnectionCount());

		Message<byte[]> message = this.outboundChannel.getMessages().get(0);
		StompHeaderAccessor accessor = StompHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		assertEquals(StompCommand.ERROR, accessor.getCommand());
		assertEquals("sess1", accessor.getSessionId());
		assertEquals("joe", accessor.getUser().getName());
		assertEquals("Broker not available.", accessor.getMessage());
	}
