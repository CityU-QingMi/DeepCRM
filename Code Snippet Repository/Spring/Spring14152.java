	@Test
	@SuppressWarnings({"", ""})
	public void handleWebSocketMessage() throws Exception {
		String text = "SEND\na:alpha\n\nMessage payload\0";
		connect().handleMessage(this.webSocketSession, new TextMessage(text));

		ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
		verify(this.stompSession).handleMessage(captor.capture());
		Message<byte[]> message = captor.getValue();
		assertNotNull(message);

		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		StompHeaders headers = StompHeaders.readOnlyStompHeaders(accessor.toNativeHeaderMap());
		assertEquals(StompCommand.SEND, accessor.getCommand());
		assertEquals("alpha", headers.getFirst("a"));
		assertEquals("Message payload", new String(message.getPayload(), StandardCharsets.UTF_8));
	}
