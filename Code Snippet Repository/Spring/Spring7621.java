	@Test
	public void connectWhenBrokerNotAvailable() throws Exception {

		this.brokerRelay.start();
		this.brokerRelay.stopInternal();
		this.brokerRelay.handleMessage(connectMessage("sess1", "joe"));

		Message<byte[]> message = this.outboundChannel.getMessages().get(0);
		StompHeaderAccessor accessor = StompHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		assertEquals(StompCommand.ERROR, accessor.getCommand());
		assertEquals("sess1", accessor.getSessionId());
		assertEquals("joe", accessor.getUser().getName());
		assertEquals("Broker not available.", accessor.getMessage());
	}
