	@Test
	public void sendToClassDefaultEmptyAnnotation() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		Message<?> inputMessage = createMessage(sessionId, "sub1", null, null, null);
		this.handler.handleReturnValue(PAYLOAD, this.defaultEmptyAnnotation, inputMessage);

		verify(this.messageChannel, times(1)).send(this.messageCaptor.capture());
		assertResponse(this.defaultEmptyAnnotation, sessionId, 0, "/dest-default");
	}
