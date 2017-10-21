	@Test
	public void decodeFrameWithNullOctectsInTheBody() {
		Message<byte[]> frame = decode("SEND\ncontent-length:23\n\nThe b\0dy \0f the message\0");
		StompHeaderAccessor headers = StompHeaderAccessor.wrap(frame);

		assertEquals(StompCommand.SEND, headers.getCommand());

		assertEquals(1, headers.toNativeHeaderMap().size());
		assertEquals(Integer.valueOf(23), headers.getContentLength());

		String bodyText = new String(frame.getPayload());
		assertEquals("The b\0dy \0f the message", bodyText);
	}
