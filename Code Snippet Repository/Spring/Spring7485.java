	@Test
	public void sendToNoAnnotations() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		Message<?> inputMessage = createMessage(sessionId, "sub1", "/app", "/dest", null);
		this.handler.handleReturnValue(PAYLOAD, this.noAnnotationsReturnType, inputMessage);

		verify(this.messageChannel, times(1)).send(this.messageCaptor.capture());
		assertResponse(this.noAnnotationsReturnType, sessionId, 0, "/topic/dest");
	}
