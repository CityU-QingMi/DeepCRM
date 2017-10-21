	@Test
	public void brokerChannelUsedByAnnotatedMethod() {
		TestChannel channel = this.simpleBrokerContext.getBean("brokerChannel", TestChannel.class);
		SimpAnnotationMethodMessageHandler messageHandler =
				this.simpleBrokerContext.getBean(SimpAnnotationMethodMessageHandler.class);

		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.SEND);
		headers.setSessionId("sess1");
		headers.setSessionAttributes(new ConcurrentHashMap<>());
		headers.setDestination("/foo");
		Message<?> message = MessageBuilder.createMessage(new byte[0], headers.getMessageHeaders());

		messageHandler.handleMessage(message);

		message = channel.messages.get(0);
		headers = StompHeaderAccessor.wrap(message);

		assertEquals(SimpMessageType.MESSAGE, headers.getMessageType());
		assertEquals("/bar", headers.getDestination());
		assertEquals("bar", new String((byte[]) message.getPayload()));
	}
