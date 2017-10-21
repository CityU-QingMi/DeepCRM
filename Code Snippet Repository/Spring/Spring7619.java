	@Test
	public void destinationExcluded() throws Exception {

		this.brokerRelay.start();

		SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
		headers.setSessionId("sess1");
		headers.setDestination("/user/daisy/foo");
		this.brokerRelay.handleMessage(MessageBuilder.createMessage(new byte[0], headers.getMessageHeaders()));

		assertEquals(1, this.tcpClient.getSentMessages().size());
		StompHeaderAccessor headers1 = this.tcpClient.getSentHeaders(0);
		assertEquals(StompCommand.CONNECT, headers1.getCommand());
		assertEquals(StompBrokerRelayMessageHandler.SYSTEM_SESSION_ID, headers1.getSessionId());
	}
