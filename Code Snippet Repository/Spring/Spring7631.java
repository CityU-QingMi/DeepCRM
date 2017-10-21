	@Test
	public void decodeFrameWithInvalidContentLength() {
		Message<byte[]> message = decode("SEND\ncontent-length:-1\n\nThe body of the message\0");
		StompHeaderAccessor headers = StompHeaderAccessor.wrap(message);

		assertEquals(StompCommand.SEND, headers.getCommand());

		assertEquals(1, headers.toNativeHeaderMap().size());
		assertEquals(Integer.valueOf(-1), headers.getContentLength());

		String bodyText = new String(message.getPayload());
		assertEquals("The body of the message", bodyText);
	}
