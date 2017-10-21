	@Test
	public void handleMessageFrameWithConversionException() throws Exception {
		this.session.afterConnected(this.connection);
		assertTrue(this.session.isConnected());

		StompFrameHandler frameHandler = mock(StompFrameHandler.class);
		String destination = "/topic/foo";
		Subscription subscription = this.session.subscribe(destination, frameHandler);

		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.MESSAGE);
		accessor.setDestination(destination);
		accessor.setSubscriptionId(subscription.getSubscriptionId());
		accessor.setContentType(MimeTypeUtils.APPLICATION_JSON);
		accessor.setMessageId("1");
		accessor.setLeaveMutable(true);
		byte[] payload = "{'foo':'bar'}".getBytes(StandardCharsets.UTF_8);

		StompHeaders stompHeaders = StompHeaders.readOnlyStompHeaders(accessor.getNativeHeaders());
		when(frameHandler.getPayloadType(stompHeaders)).thenReturn(Map.class);

		this.session.handleMessage(MessageBuilder.createMessage(payload, accessor.getMessageHeaders()));

		verify(frameHandler).getPayloadType(stompHeaders);
		verifyNoMoreInteractions(frameHandler);

		verify(this.sessionHandler).handleException(same(this.session), same(StompCommand.MESSAGE),
				eq(stompHeaders), same(payload), any(MessageConversionException.class));
		verifyNoMoreInteractions(this.sessionHandler);
	}
