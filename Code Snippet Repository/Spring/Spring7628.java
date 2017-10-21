	@Test
	public void decodeHeartbeat() {
		String frame = "\n";

		ByteBuffer buffer = ByteBuffer.wrap(frame.getBytes());

		final List<Message<byte[]>> messages = decoder.decode(buffer);

		assertEquals(1, messages.size());
		assertEquals(SimpMessageType.HEARTBEAT, StompHeaderAccessor.wrap(messages.get(0)).getMessageType());
	}
