	@Test
	public void decodeFrameWithEscapedHeaders() {
		Message<byte[]> frame = decode("DISCONNECT\na\\c\\r\\n\\\\b:alpha\\cbravo\\r\\n\\\\\n\n\0");
		StompHeaderAccessor headers = StompHeaderAccessor.wrap(frame);

		assertEquals(StompCommand.DISCONNECT, headers.getCommand());

		assertEquals(1, headers.toNativeHeaderMap().size());
		assertEquals("alpha:bravo\r\n\\", headers.getFirstNativeHeader("a:\r\n\\b"));
	}
