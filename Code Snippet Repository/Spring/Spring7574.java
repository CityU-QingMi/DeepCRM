	@Test
	public void send() throws Exception {
		this.session.afterConnected(this.connection);
		assertTrue(this.session.isConnected());

		String destination = "/topic/foo";
		String payload = "sample payload";
		this.session.send(destination, payload);

		Message<byte[]> message = this.messageCaptor.getValue();
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		assertEquals(StompCommand.SEND, accessor.getCommand());

		StompHeaders stompHeaders = StompHeaders.readOnlyStompHeaders(accessor.getNativeHeaders());
		assertEquals(stompHeaders.toString(), 2, stompHeaders.size());

		assertEquals(destination, stompHeaders.getDestination());
		assertEquals(new MimeType("text", "plain", StandardCharsets.UTF_8), stompHeaders.getContentType());
		assertEquals(-1, stompHeaders.getContentLength());  // StompEncoder isn't involved
		assertEquals(payload, new String(message.getPayload(), StandardCharsets.UTF_8));
	}
