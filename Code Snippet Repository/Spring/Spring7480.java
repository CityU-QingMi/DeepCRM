	@Test
	public void testHeadersToSend() throws Exception {
		Message<?> message = createMessage("sess1", "sub1", "/app", "/dest", null);

		SimpMessageSendingOperations messagingTemplate = Mockito.mock(SimpMessageSendingOperations.class);
		SendToMethodReturnValueHandler handler = new SendToMethodReturnValueHandler(messagingTemplate, false);

		handler.handleReturnValue(PAYLOAD, this.noAnnotationsReturnType, message);

		ArgumentCaptor<MessageHeaders> captor = ArgumentCaptor.forClass(MessageHeaders.class);
		verify(messagingTemplate).convertAndSend(eq("/topic/dest"), eq(PAYLOAD), captor.capture());

		MessageHeaders headers = captor.getValue();
		SimpMessageHeaderAccessor accessor =
				MessageHeaderAccessor.getAccessor(headers, SimpMessageHeaderAccessor.class);
		assertNotNull(accessor);
		assertTrue(accessor.isMutable());
		assertEquals("sess1", accessor.getSessionId());
		assertNull("Subscription id should not be copied", accessor.getSubscriptionId());
		assertEquals(this.noAnnotationsReturnType,
				accessor.getHeader(SimpMessagingTemplate.CONVERSION_HINT_HEADER));
	}
