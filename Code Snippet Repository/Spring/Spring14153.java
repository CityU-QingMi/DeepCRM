	@Test
	@SuppressWarnings({"", ""})
	public void handleWebSocketMessageSplitAcrossTwoMessage() throws Exception {
		WebSocketHandler webSocketHandler = connect();

		String part1 = "SEND\na:alpha\n\nMessage";
		webSocketHandler.handleMessage(this.webSocketSession, new TextMessage(part1));

		verifyNoMoreInteractions(this.stompSession);

		String part2 = " payload\0";
		webSocketHandler.handleMessage(this.webSocketSession, new TextMessage(part2));

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
