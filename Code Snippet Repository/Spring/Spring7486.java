	@Test
	public void sendToUserDefaultDestination() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		TestUser user = new TestUser();
		Message<?> inputMessage = createMessage(sessionId, "sub1", "/app", "/dest", user);
		this.handler.handleReturnValue(PAYLOAD, this.sendToUserDefaultDestReturnType, inputMessage);

		verify(this.messageChannel, times(1)).send(this.messageCaptor.capture());

		SimpMessageHeaderAccessor accessor = getCapturedAccessor(0);
		assertNull(accessor.getSessionId());
		assertNull(accessor.getSubscriptionId());
		assertEquals("/user/" + user.getName() + "/queue/dest", accessor.getDestination());
	}
