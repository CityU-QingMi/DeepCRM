	@Test
	public void sendToUser() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		TestUser user = new TestUser();
		Message<?> inputMessage = createMessage(sessionId, "sub1", null, null, user);
		this.handler.handleReturnValue(PAYLOAD, this.sendToUserReturnType, inputMessage);

		verify(this.messageChannel, times(2)).send(this.messageCaptor.capture());

		SimpMessageHeaderAccessor accessor = getCapturedAccessor(0);
		assertNull(accessor.getSessionId());
		assertNull(accessor.getSubscriptionId());
		assertEquals("/user/" + user.getName() + "/dest1", accessor.getDestination());

		accessor = getCapturedAccessor(1);
		assertNull(accessor.getSessionId());
		assertNull(accessor.getSubscriptionId());
		assertEquals("/user/" + user.getName() + "/dest2", accessor.getDestination());
	}
