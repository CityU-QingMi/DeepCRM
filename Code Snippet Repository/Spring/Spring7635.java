	@Test
	public void decodeMultipleFramesFromSameBuffer() {
		String frame1 = "SEND\ndestination:test\n\nThe body of the message\0";
		String frame2 = "DISCONNECT\n\n\0";
		ByteBuffer buffer = ByteBuffer.wrap((frame1 + frame2).getBytes());

		final List<Message<byte[]>> messages = decoder.decode(buffer);

		assertEquals(2, messages.size());
		assertEquals(StompCommand.SEND, StompHeaderAccessor.wrap(messages.get(0)).getCommand());
		assertEquals(StompCommand.DISCONNECT, StompHeaderAccessor.wrap(messages.get(1)).getCommand());
	}
