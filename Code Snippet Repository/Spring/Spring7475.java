	@Test
	public void sendToUserClassDefaultOverride() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		Message<?> inputMessage = createMessage(sessionId, "sub1", null, null, null);
		this.handler.handleReturnValue(PAYLOAD, this.userDefaultOverrideAnnotation, inputMessage);

		verify(this.messageChannel, times(2)).send(this.messageCaptor.capture());
		assertResponse(this.userDefaultOverrideAnnotation, sessionId, 0, "/user/sess1/dest3");
		assertResponse(this.userDefaultOverrideAnnotation, sessionId, 1, "/user/sess1/dest4");
	}
