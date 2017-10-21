	@Test
	public void encodeFrameWithHeadersBody() {
		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.SEND);
		headers.addNativeHeader("a", "alpha");
		Message<byte[]> frame = MessageBuilder.createMessage(
				"Message body".getBytes(), headers.getMessageHeaders());

		assertEquals("SEND\na:alpha\ncontent-length:12\n\nMessage body\0",
				new String(encoder.encode(frame)));
	}
