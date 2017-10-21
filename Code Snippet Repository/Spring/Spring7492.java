	@Test
	public void sendTo() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		Message<?> inputMessage = createMessage(sessionId, "sub1", null, null, null);
		this.handler.handleReturnValue(PAYLOAD, this.sendToReturnType, inputMessage);

		verify(this.messageChannel, times(2)).send(this.messageCaptor.capture());
		assertResponse(this.sendToReturnType, sessionId, 0, "/dest1");
		assertResponse(this.sendToReturnType, sessionId, 1, "/dest2");
	}
