	@Test
	public void sendWebSocketBinary() throws Exception {
		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.SEND);
		accessor.setDestination("/b");
		accessor.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM);
		byte[] payload = "payload".getBytes(StandardCharsets.UTF_8);

		getTcpConnection().send(MessageBuilder.createMessage(payload, accessor.getMessageHeaders()));

		ArgumentCaptor<BinaryMessage> binaryMessageCaptor = ArgumentCaptor.forClass(BinaryMessage.class);
		verify(this.webSocketSession).sendMessage(binaryMessageCaptor.capture());
		BinaryMessage binaryMessage = binaryMessageCaptor.getValue();
		assertNotNull(binaryMessage);
		assertEquals("SEND\ndestination:/b\ncontent-type:application/octet-stream\ncontent-length:7\n\npayload\0",
				new String(binaryMessage.getPayload().array(), StandardCharsets.UTF_8));
	}
