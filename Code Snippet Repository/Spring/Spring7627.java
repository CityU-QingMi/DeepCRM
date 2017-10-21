	@Test
	public void decodeFrameWithNoBody() {
		String accept = "accept-version:1.1\n";
		String host = "host:github.org\n";

		Message<byte[]> frame = decode("CONNECT\n" + accept + host + "\n\0");
		StompHeaderAccessor headers = StompHeaderAccessor.wrap(frame);

		assertEquals(StompCommand.CONNECT, headers.getCommand());

		assertEquals(2, headers.toNativeHeaderMap().size());
		assertEquals("1.1", headers.getFirstNativeHeader("accept-version"));
		assertEquals("github.org", headers.getHost());

		assertEquals(0, frame.getPayload().length);
	}
