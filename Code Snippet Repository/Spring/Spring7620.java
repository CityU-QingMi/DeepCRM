	@Test
	public void messageFromBrokerIsEnriched() throws Exception {

		this.brokerRelay.start();
		this.brokerRelay.handleMessage(connectMessage("sess1", "joe"));

		assertEquals(2, this.tcpClient.getSentMessages().size());
		assertEquals(StompCommand.CONNECT, this.tcpClient.getSentHeaders(0).getCommand());
		assertEquals(StompCommand.CONNECT, this.tcpClient.getSentHeaders(1).getCommand());

		this.tcpClient.handleMessage(message(StompCommand.MESSAGE, null, null, null));

		Message<byte[]> message = this.outboundChannel.getMessages().get(0);
		StompHeaderAccessor accessor = StompHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		assertEquals("sess1", accessor.getSessionId());
		assertEquals("joe", accessor.getUser().getName());
	}
