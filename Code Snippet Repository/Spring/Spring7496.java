	@Test
	public void sendToClassDefaultOverride() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		Message<?> inputMessage = createMessage(sessionId, "sub1", null, null, null);
		this.handler.handleReturnValue(PAYLOAD, this.defaultOverrideAnnotation, inputMessage);

		verify(this.messageChannel, times(2)).send(this.messageCaptor.capture());
		assertResponse(this.defaultOverrideAnnotation, sessionId, 0, "/dest3");
		assertResponse(this.defaultOverrideAnnotation, sessionId, 1, "/dest4");
	}
