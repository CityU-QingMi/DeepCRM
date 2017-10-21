	@Test
	public void sendToDefaultDestination() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		Message<?> inputMessage = createMessage(sessionId, "sub1", "/app", "/dest", null);
		this.handler.handleReturnValue(PAYLOAD, this.sendToDefaultDestReturnType, inputMessage);

		verify(this.messageChannel, times(1)).send(this.messageCaptor.capture());
		assertResponse(this.sendToDefaultDestReturnType, sessionId, 0, "/topic/dest");
	}
