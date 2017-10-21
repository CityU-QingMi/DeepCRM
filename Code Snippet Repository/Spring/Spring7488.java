	@Test
	public void sendToUserDefaultDestinationSingleSession() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		TestUser user = new TestUser();
		Message<?> message = createMessage(sessionId, "sub1", "/app", "/dest", user);
		this.handler.handleReturnValue(PAYLOAD, this.sendToUserSingleSessionDefaultDestReturnType, message);

		verify(this.messageChannel, times(1)).send(this.messageCaptor.capture());

		SimpMessageHeaderAccessor accessor = getCapturedAccessor(0);
		assertEquals(sessionId, accessor.getSessionId());
		assertEquals("/user/" + user.getName() + "/queue/dest", accessor.getDestination());
		assertEquals(MIME_TYPE, accessor.getContentType());
		assertNull("Subscription id should not be copied", accessor.getSubscriptionId());
		assertEquals(this.sendToUserSingleSessionDefaultDestReturnType,
				accessor.getHeader(SimpMessagingTemplate.CONVERSION_HINT_HEADER));
	}
