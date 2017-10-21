	@Test
	public void handleMessageFromClientWithImmutableMessageInterceptor() {
		AtomicReference<Boolean> mutable = new AtomicReference<>();
		ExecutorSubscribableChannel channel = new ExecutorSubscribableChannel();
		channel.addInterceptor(new ChannelInterceptorAdapter() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				mutable.set(MessageHeaderAccessor.getAccessor(message, MessageHeaderAccessor.class).isMutable());
				return message;
			}
		});
		channel.addInterceptor(new ImmutableMessageChannelInterceptor());

		StompSubProtocolHandler handler = new StompSubProtocolHandler();
		handler.afterSessionStarted(this.session, channel);

		TextMessage message = StompTextMessageBuilder.create(StompCommand.CONNECT).build();
		handler.handleMessageFromClient(this.session, message, channel);
		assertNotNull(mutable.get());
		assertTrue(mutable.get());
	}
