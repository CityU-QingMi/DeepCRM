	@Test
	public void sendToUserClassDefaultEmptyAnnotation() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		Message<?> inputMessage = createMessage(sessionId, "sub1", null, null, null);
		this.handler.handleReturnValue(PAYLOAD, this.userDefaultEmptyAnnotation, inputMessage);

		verify(this.messageChannel, times(1)).send(this.messageCaptor.capture());
		assertResponse(this.userDefaultEmptyAnnotation, sessionId, 0, "/user/sess1/dest-default");
	}
