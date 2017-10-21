	@Test
	public void sendToUserWithUserNameProvider() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		TestUser user = new UniqueUser();
		Message<?> inputMessage = createMessage(sessionId, "sub1", null, null, user);
		this.handler.handleReturnValue(PAYLOAD, this.sendToUserReturnType, inputMessage);

		verify(this.messageChannel, times(2)).send(this.messageCaptor.capture());

		SimpMessageHeaderAccessor accessor = getCapturedAccessor(0);
		assertEquals("/user/Me myself and I/dest1", accessor.getDestination());

		accessor = getCapturedAccessor(1);
		assertEquals("/user/Me myself and I/dest2", accessor.getDestination());
	}
