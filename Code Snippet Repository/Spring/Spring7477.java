	@Test
	public void sendToUserWithSendToOverride() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		Class<?> clazz = SendToUserWithSendToOverrideTestBean.class;
		Method method = clazz.getDeclaredMethod("handleAndSendToOverride");
		MethodParameter parameter = new SynthesizingMethodParameter(method, -1);

		String sessionId = "sess1";
		Message<?> inputMessage = createMessage(sessionId, "sub1", null, null, null);
		this.handler.handleReturnValue(PAYLOAD, parameter, inputMessage);

		verify(this.messageChannel, times(2)).send(this.messageCaptor.capture());
		assertResponse(parameter, sessionId, 0, "/dest3");
		assertResponse(parameter, sessionId, 1, "/dest4");
	}
