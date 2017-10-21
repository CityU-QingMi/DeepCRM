	@Test
	public void unsubscribe() throws Exception {
		this.session.afterConnected(this.connection);
		assertTrue(this.session.isConnected());

		String destination = "/topic/foo";
		StompFrameHandler frameHandler = mock(StompFrameHandler.class);
		Subscription subscription = this.session.subscribe(destination, frameHandler);
		subscription.unsubscribe();

		Message<byte[]> message = this.messageCaptor.getValue();
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		assertEquals(StompCommand.UNSUBSCRIBE, accessor.getCommand());

		StompHeaders stompHeaders = StompHeaders.readOnlyStompHeaders(accessor.getNativeHeaders());
		assertEquals(stompHeaders.toString(), 1, stompHeaders.size());
		assertEquals(subscription.getSubscriptionId(), stompHeaders.getId());
	}
