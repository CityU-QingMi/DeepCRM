	@Test
	public void clientOutboundChannelUsedByAnnotatedMethod() {
		TestChannel channel = this.simpleBrokerContext.getBean("clientOutboundChannel", TestChannel.class);
		SimpAnnotationMethodMessageHandler messageHandler = this.simpleBrokerContext.getBean(SimpAnnotationMethodMessageHandler.class);

		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.SUBSCRIBE);
		headers.setSessionId("sess1");
		headers.setSessionAttributes(new ConcurrentHashMap<>());
		headers.setSubscriptionId("subs1");
		headers.setDestination("/foo");
		Message<?> message = MessageBuilder.withPayload(new byte[0]).setHeaders(headers).build();

		messageHandler.handleMessage(message);

		message = channel.messages.get(0);
		headers = StompHeaderAccessor.wrap(message);

		assertEquals(SimpMessageType.MESSAGE, headers.getMessageType());
		assertEquals("/foo", headers.getDestination());
		assertEquals("bar", new String((byte[]) message.getPayload()));
	}
