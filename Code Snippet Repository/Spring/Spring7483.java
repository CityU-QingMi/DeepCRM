	@Test
	public void sendToUserSingleSession() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		TestUser user = new TestUser();
		Message<?> inputMessage = createMessage(sessionId, "sub1", null, null, user);
		this.handler.handleReturnValue(PAYLOAD, this.sendToUserSingleSessionReturnType, inputMessage);

		verify(this.messageChannel, times(2)).send(this.messageCaptor.capture());

		SimpMessageHeaderAccessor accessor = getCapturedAccessor(0);
		assertEquals(sessionId, accessor.getSessionId());
		assertEquals(MIME_TYPE, accessor.getContentType());
		assertEquals("/user/" + user.getName() + "/dest1", accessor.getDestination());
		assertNull("Subscription id should not be copied", accessor.getSubscriptionId());
		assertEquals(this.sendToUserSingleSessionReturnType,
				accessor.getHeader(SimpMessagingTemplate.CONVERSION_HINT_HEADER));

		accessor = getCapturedAccessor(1);
		assertEquals(sessionId, accessor.getSessionId());
		assertEquals("/user/" + user.getName() + "/dest2", accessor.getDestination());
		assertEquals(MIME_TYPE, accessor.getContentType());
		assertNull("Subscription id should not be copied", accessor.getSubscriptionId());
		assertEquals(this.sendToUserSingleSessionReturnType,
				accessor.getHeader(SimpMessagingTemplate.CONVERSION_HINT_HEADER));
	}
