	@Test
	public void decodeFrame() throws UnsupportedEncodingException {
		Message<byte[]> frame = decode("SEND\ndestination:test\n\nThe body of the message\0");
		StompHeaderAccessor headers = StompHeaderAccessor.wrap(frame);

		assertEquals(StompCommand.SEND, headers.getCommand());

		assertEquals(headers.toNativeHeaderMap().toString(), 1, headers.toNativeHeaderMap().size());
		assertEquals("test", headers.getDestination());

		String bodyText = new String(frame.getPayload());
		assertEquals("The body of the message", bodyText);
	}
