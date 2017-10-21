	@Test
	@SuppressWarnings({ "", "" })
	public void testHeadersPassedToMessagingTemplate() throws Exception {
		String sessionId = "sess1";
		String subscriptionId = "subs1";
		String destination = "/dest";
		Message<?> inputMessage = createInputMessage(sessionId, subscriptionId, destination, null);

		MessageSendingOperations messagingTemplate = Mockito.mock(MessageSendingOperations.class);
		SubscriptionMethodReturnValueHandler handler = new SubscriptionMethodReturnValueHandler(messagingTemplate);

		handler.handleReturnValue(PAYLOAD, this.subscribeEventReturnType, inputMessage);

		ArgumentCaptor<MessageHeaders> captor = ArgumentCaptor.forClass(MessageHeaders.class);
		verify(messagingTemplate).convertAndSend(eq("/dest"), eq(PAYLOAD), captor.capture());

		SimpMessageHeaderAccessor headerAccessor =
				MessageHeaderAccessor.getAccessor(captor.getValue(), SimpMessageHeaderAccessor.class);

		assertNotNull(headerAccessor);
		assertTrue(headerAccessor.isMutable());
		assertEquals(sessionId, headerAccessor.getSessionId());
		assertEquals(subscriptionId, headerAccessor.getSubscriptionId());
		assertEquals(this.subscribeEventReturnType, headerAccessor.getHeader(SimpMessagingTemplate.CONVERSION_HINT_HEADER));
	}
