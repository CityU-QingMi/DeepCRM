	@Test
	public void subscribeWithHeaders() throws Exception {
		this.session.afterConnected(this.connection);
		assertTrue(this.session.isConnected());

		String subscriptionId = "123";
		String destination = "/topic/foo";

		StompHeaders stompHeaders = new StompHeaders();
		stompHeaders.setId(subscriptionId);
		stompHeaders.setDestination(destination);
		StompFrameHandler frameHandler = mock(StompFrameHandler.class);

		Subscription subscription = this.session.subscribe(stompHeaders, frameHandler);
		assertEquals(subscriptionId, subscription.getSubscriptionId());

		Message<byte[]> message = this.messageCaptor.getValue();
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		assertEquals(StompCommand.SUBSCRIBE, accessor.getCommand());

		stompHeaders = StompHeaders.readOnlyStompHeaders(accessor.getNativeHeaders());
		assertEquals(stompHeaders.toString(), 2, stompHeaders.size());
		assertEquals(destination, stompHeaders.getDestination());
		assertEquals(subscriptionId, stompHeaders.getId());
	}
