	@Test
	public void decodeFrameWithContentLength() {
		Message<byte[]> message = decode("SEND\ncontent-length:23\n\nThe body of the message\0");
		StompHeaderAccessor headers = StompHeaderAccessor.wrap(message);

		assertEquals(StompCommand.SEND, headers.getCommand());

		assertEquals(1, headers.toNativeHeaderMap().size());
		assertEquals(Integer.valueOf(23), headers.getContentLength());

		String bodyText = new String(message.getPayload());
		assertEquals("The body of the message", bodyText);
	}
