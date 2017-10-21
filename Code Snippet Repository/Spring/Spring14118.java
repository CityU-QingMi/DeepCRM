	@Test
	public void handleMessageFromClientWithTokenAuthentication() {
		ExecutorSubscribableChannel channel = new ExecutorSubscribableChannel();
		channel.addInterceptor(new AuthenticationInterceptor("__pete__@gmail.com"));
		channel.addInterceptor(new ImmutableMessageChannelInterceptor());

		TestMessageHandler messageHandler = new TestMessageHandler();
		channel.subscribe(messageHandler);

		StompSubProtocolHandler handler = new StompSubProtocolHandler();
		handler.afterSessionStarted(this.session, channel);

		TextMessage wsMessage = StompTextMessageBuilder.create(StompCommand.CONNECT).build();
		handler.handleMessageFromClient(this.session, wsMessage, channel);

		assertEquals(1, messageHandler.getMessages().size());
		Message<?> message = messageHandler.getMessages().get(0);
		Principal user = SimpMessageHeaderAccessor.getUser(message.getHeaders());
		assertNotNull(user);
		assertEquals("__pete__@gmail.com", user.getName());
	}
