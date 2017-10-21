	@Test
	public void sendWithTimeoutMutable() {
		SubscribableChannel channel = mock(SubscribableChannel.class);
		final AtomicReference<Message<?>> sent = new AtomicReference<>();
		doAnswer(invocation -> {
			sent.set(invocation.getArgument(0));
			return true;
		}).when(channel).send(any(Message.class), eq(30_000L));
		MessageHeaderAccessor accessor = new MessageHeaderAccessor();
		accessor.setLeaveMutable(true);
		Message<?> message = new GenericMessage<>("request", accessor.getMessageHeaders());
		accessor.setHeader(GenericMessagingTemplate.DEFAULT_SEND_TIMEOUT_HEADER, 30_000L);
		this.template.send(channel, message);
		verify(channel).send(any(Message.class), eq(30_000L));
		assertNotNull(sent.get());
		assertFalse(sent.get().getHeaders().containsKey(GenericMessagingTemplate.DEFAULT_SEND_TIMEOUT_HEADER));
		assertFalse(sent.get().getHeaders().containsKey(GenericMessagingTemplate.DEFAULT_RECEIVE_TIMEOUT_HEADER));
	}
