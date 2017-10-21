	@Test
	public void sendToUserSessionWithoutUserName() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		Message<?> inputMessage = createMessage(sessionId, "sub1", null, null, null);
		this.handler.handleReturnValue(PAYLOAD, this.sendToUserReturnType, inputMessage);

		verify(this.messageChannel, times(2)).send(this.messageCaptor.capture());

		SimpMessageHeaderAccessor accessor = getCapturedAccessor(0);
		assertEquals("/user/sess1/dest1", accessor.getDestination());
		assertEquals("sess1", accessor.getSessionId());

		accessor = getCapturedAccessor(1);
		assertEquals("/user/sess1/dest2", accessor.getDestination());
		assertEquals("sess1", accessor.getSessionId());
	}
