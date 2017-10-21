	@Test
	public void sendWebSocketMessage() throws Exception {
		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.SEND);
		accessor.setDestination("/topic/foo");
		byte[] payload = "payload".getBytes(StandardCharsets.UTF_8);

		getTcpConnection().send(MessageBuilder.createMessage(payload, accessor.getMessageHeaders()));

		ArgumentCaptor<TextMessage> textMessageCaptor = ArgumentCaptor.forClass(TextMessage.class);
		verify(this.webSocketSession).sendMessage(textMessageCaptor.capture());
		TextMessage textMessage = textMessageCaptor.getValue();
		assertNotNull(textMessage);
		assertEquals("SEND\ndestination:/topic/foo\ncontent-length:7\n\npayload\0", textMessage.getPayload());
	}
