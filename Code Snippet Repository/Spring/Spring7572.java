	@Test
	public void handleMessageFrame() throws Exception {
		this.session.afterConnected(this.connection);

		StompFrameHandler frameHandler = mock(StompFrameHandler.class);
		String destination = "/topic/foo";
		Subscription subscription = this.session.subscribe(destination, frameHandler);

		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.MESSAGE);
		accessor.setDestination(destination);
		accessor.setSubscriptionId(subscription.getSubscriptionId());
		accessor.setContentType(MimeTypeUtils.TEXT_PLAIN);
		accessor.setMessageId("1");
		accessor.setLeaveMutable(true);
		String payload = "sample payload";

		StompHeaders stompHeaders = StompHeaders.readOnlyStompHeaders(accessor.getNativeHeaders());
		when(frameHandler.getPayloadType(stompHeaders)).thenReturn(String.class);

		this.session.handleMessage(MessageBuilder.createMessage(payload.getBytes(StandardCharsets.UTF_8),
				accessor.getMessageHeaders()));

		verify(frameHandler).getPayloadType(stompHeaders);
		verify(frameHandler).handleFrame(stompHeaders, payload);
		verifyNoMoreInteractions(frameHandler);
	}
