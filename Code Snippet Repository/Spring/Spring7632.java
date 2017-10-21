	@Test
	public void decodeFrameWithContentLengthZero() {
		Message<byte[]> frame = decode("SEND\ncontent-length:0\n\n\0");
		StompHeaderAccessor headers = StompHeaderAccessor.wrap(frame);

		assertEquals(StompCommand.SEND, headers.getCommand());

		assertEquals(1, headers.toNativeHeaderMap().size());
		assertEquals(Integer.valueOf(0), headers.getContentLength());

		String bodyText = new String(frame.getPayload());
		assertEquals("", bodyText);
	}
