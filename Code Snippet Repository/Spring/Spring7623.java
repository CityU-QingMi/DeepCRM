	@Test
	public void systemSubscription() throws Exception {

		MessageHandler handler = mock(MessageHandler.class);
		this.brokerRelay.setSystemSubscriptions(Collections.singletonMap("/topic/foo", handler));
		this.brokerRelay.start();

		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.CONNECTED);
		accessor.setLeaveMutable(true);
		MessageHeaders headers = accessor.getMessageHeaders();
		this.tcpClient.handleMessage(MessageBuilder.createMessage(new byte[0], headers));

		assertEquals(2, this.tcpClient.getSentMessages().size());
		assertEquals(StompCommand.CONNECT, this.tcpClient.getSentHeaders(0).getCommand());
		assertEquals(StompCommand.SUBSCRIBE, this.tcpClient.getSentHeaders(1).getCommand());
		assertEquals("/topic/foo", this.tcpClient.getSentHeaders(1).getDestination());

		Message<byte[]> message = message(StompCommand.MESSAGE, null, null, "/topic/foo");
		this.tcpClient.handleMessage(message);

		ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
		verify(handler).handleMessage(captor.capture());
		assertSame(message, captor.getValue());
	}
