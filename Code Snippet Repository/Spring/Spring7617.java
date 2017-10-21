	@Test
	public void virtualHost() throws Exception {

		this.brokerRelay.setVirtualHost("ABC");

		this.brokerRelay.start();
		this.brokerRelay.handleMessage(connectMessage("sess1", "joe"));

		assertEquals(2, this.tcpClient.getSentMessages().size());

		StompHeaderAccessor headers1 = this.tcpClient.getSentHeaders(0);
		assertEquals(StompCommand.CONNECT, headers1.getCommand());
		assertEquals(StompBrokerRelayMessageHandler.SYSTEM_SESSION_ID, headers1.getSessionId());
		assertEquals("ABC", headers1.getHost());

		StompHeaderAccessor headers2 = this.tcpClient.getSentHeaders(1);
		assertEquals(StompCommand.CONNECT, headers2.getCommand());
		assertEquals("sess1", headers2.getSessionId());
		assertEquals("ABC", headers2.getHost());
	}
