	@Test
	public void encodeFrameWithContentLengthPresent() {
		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.SEND);
		headers.setContentLength(12);
		Message<byte[]> frame = MessageBuilder.createMessage(
				"Message body".getBytes(), headers.getMessageHeaders());

		assertEquals("SEND\ncontent-length:12\n\nMessage body\0",
				new String(encoder.encode(frame)));
	}
