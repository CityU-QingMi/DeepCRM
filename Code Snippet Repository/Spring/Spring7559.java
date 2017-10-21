	@Test
	public void clientOutboundChannelUsedBySimpleBroker() {
		TestChannel channel = this.simpleBrokerContext.getBean("clientOutboundChannel", TestChannel.class);
		SimpleBrokerMessageHandler broker = this.simpleBrokerContext.getBean(SimpleBrokerMessageHandler.class);

		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.SUBSCRIBE);
		headers.setSessionId("sess1");
		headers.setSubscriptionId("subs1");
		headers.setDestination("/foo");
		Message<?> message = MessageBuilder.createMessage(new byte[0], headers.getMessageHeaders());

		// subscribe
		broker.handleMessage(message);

		headers = StompHeaderAccessor.create(StompCommand.SEND);
		headers.setSessionId("sess1");
		headers.setDestination("/foo");
		message = MessageBuilder.createMessage("bar".getBytes(), headers.getMessageHeaders());

		// message
		broker.handleMessage(message);

		message = channel.messages.get(0);
		headers = StompHeaderAccessor.wrap(message);

		assertEquals(SimpMessageType.MESSAGE, headers.getMessageType());
		assertEquals("/foo", headers.getDestination());
		assertEquals("bar", new String((byte[]) message.getPayload()));
	}
