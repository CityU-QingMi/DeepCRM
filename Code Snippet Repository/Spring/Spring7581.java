	@Test
	public void unsubscribeWithCustomHeader() throws Exception {
		this.session.afterConnected(this.connection);
		assertTrue(this.session.isConnected());

		String headerName = "durable-subscription-name";
		String headerValue = "123";

		StompHeaders subscribeHeaders = new StompHeaders();
		subscribeHeaders.setDestination("/topic/foo");
		subscribeHeaders.set(headerName, headerValue);
		StompFrameHandler frameHandler = mock(StompFrameHandler.class);
		Subscription subscription = this.session.subscribe(subscribeHeaders, frameHandler);

		StompHeaders unsubscribeHeaders = new StompHeaders();
		unsubscribeHeaders.set(headerName,  subscription.getSubscriptionHeaders().getFirst(headerName));
		subscription.unsubscribe(unsubscribeHeaders);

		Message<byte[]> message = this.messageCaptor.getValue();
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		assertEquals(StompCommand.UNSUBSCRIBE, accessor.getCommand());

		StompHeaders stompHeaders = StompHeaders.readOnlyStompHeaders(accessor.getNativeHeaders());
		assertEquals(stompHeaders.toString(), 2, stompHeaders.size());
		assertEquals(subscription.getSubscriptionId(), stompHeaders.getId());
		assertEquals(headerValue, stompHeaders.getFirst(headerName));
	}
