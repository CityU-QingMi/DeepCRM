	@Test
	public void decodeFrameWithHeaderWithEmptyValue() {
		String accept = "accept-version:1.1\n";
		String valuelessKey = "key:\n";

		Message<byte[]> frame = decode("CONNECT\n" + accept + valuelessKey + "\n\0");
		StompHeaderAccessor headers = StompHeaderAccessor.wrap(frame);

		assertEquals(StompCommand.CONNECT, headers.getCommand());

		assertEquals(2, headers.toNativeHeaderMap().size());
		assertEquals("1.1", headers.getFirstNativeHeader("accept-version"));
		assertEquals("", headers.getFirstNativeHeader("key"));

		assertEquals(0, frame.getPayload().length);
	}
