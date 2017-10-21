	@Test
	public void testJsonView() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		String sessionId = "sess1";
		String subscriptionId = "subs1";
		String destination = "/dest";
		Message<?> inputMessage = createInputMessage(sessionId, subscriptionId, destination, null);

		this.jsonHandler.handleReturnValue(getJsonView(), this.subscribeEventJsonViewReturnType, inputMessage);

		verify(this.messageChannel).send(this.messageCaptor.capture());
		Message<?> message = this.messageCaptor.getValue();
		assertNotNull(message);

		assertEquals("{\"withView1\":\"with\"}", new String((byte[]) message.getPayload(), StandardCharsets.UTF_8));
	}
