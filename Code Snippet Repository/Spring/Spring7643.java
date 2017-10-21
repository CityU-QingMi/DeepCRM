	@Test
	public void getShortLogMessage() {
		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.SEND);
		accessor.setDestination("/foo");
		accessor.setContentType(MimeTypeUtils.APPLICATION_JSON);
		accessor.setSessionId("123");
		String actual = accessor.getShortLogMessage("payload".getBytes(StandardCharsets.UTF_8));
		assertEquals("SEND /foo session=123 application/json payload=payload", actual);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 80; i++) {
			sb.append("a");
		}
		final String payload = sb.toString() + " > 80";
		actual = accessor.getShortLogMessage(payload.getBytes(StandardCharsets.UTF_8));
		assertEquals("SEND /foo session=123 application/json payload=" + sb + "...(truncated)", actual);
	}
