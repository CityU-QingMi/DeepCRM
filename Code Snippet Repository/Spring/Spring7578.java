	@Test
	public void subscribe() throws Exception {
		this.session.afterConnected(this.connection);
		assertTrue(this.session.isConnected());

		String destination = "/topic/foo";
		StompFrameHandler frameHandler = mock(StompFrameHandler.class);
		Subscription subscription = this.session.subscribe(destination, frameHandler);

		Message<byte[]> message = this.messageCaptor.getValue();
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		assertEquals(StompCommand.SUBSCRIBE, accessor.getCommand());

		StompHeaders stompHeaders = StompHeaders.readOnlyStompHeaders(accessor.getNativeHeaders());
		assertEquals(stompHeaders.toString(), 2, stompHeaders.size());
		assertEquals(destination, stompHeaders.getDestination());
		assertEquals(subscription.getSubscriptionId(), stompHeaders.getId());
	}
