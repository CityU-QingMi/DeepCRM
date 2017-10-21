	@Test
	public void testMessageSentToChannel() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		String subscriptionId = "subs1";
		String destination = "/dest";
		Message<?> inputMessage = createInputMessage(sessionId, subscriptionId, destination, null);

		this.handler.handleReturnValue(PAYLOAD, this.subscribeEventReturnType, inputMessage);

		verify(this.messageChannel).send(this.messageCaptor.capture());
		assertNotNull(this.messageCaptor.getValue());

		Message<?> message = this.messageCaptor.getValue();
		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(message);

		assertNull("SimpMessageHeaderAccessor should have disabled id", headerAccessor.getId());
		assertNull("SimpMessageHeaderAccessor should have disabled timestamp", headerAccessor.getTimestamp());
		assertEquals(sessionId, headerAccessor.getSessionId());
		assertEquals(subscriptionId, headerAccessor.getSubscriptionId());
		assertEquals(destination, headerAccessor.getDestination());
		assertEquals(MIME_TYPE, headerAccessor.getContentType());
		assertEquals(this.subscribeEventReturnType, headerAccessor.getHeader(SimpMessagingTemplate.CONVERSION_HINT_HEADER));
	}
