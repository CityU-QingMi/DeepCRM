	@Test
	public void jsonView() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		Message<?> inputMessage = createMessage(sessionId, "sub1", "/app", "/dest", null);
		this.jsonHandler.handleReturnValue(handleAndSendToJsonView(), this.jsonViewReturnType, inputMessage);

		verify(this.messageChannel).send(this.messageCaptor.capture());
		Message<?> message = this.messageCaptor.getValue();
		assertNotNull(message);

		String bytes = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
		assertEquals("{\"withView1\":\"with\"}", bytes);
	}
