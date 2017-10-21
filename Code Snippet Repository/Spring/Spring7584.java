	@Test
	public void nack() throws Exception {
		this.session.afterConnected(this.connection);
		assertTrue(this.session.isConnected());

		String messageId = "123";
		this.session.acknowledge(messageId, false);

		Message<byte[]> message = this.messageCaptor.getValue();
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
		assertEquals(StompCommand.NACK, accessor.getCommand());

		StompHeaders stompHeaders = StompHeaders.readOnlyStompHeaders(accessor.getNativeHeaders());
		assertEquals(stompHeaders.toString(), 1, stompHeaders.size());
		assertEquals(messageId, stompHeaders.getId());
	}
