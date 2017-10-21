	@Test
	public void getShortLogMessagePayload() {
		MessageHeaderAccessor accessor = new MessageHeaderAccessor();
		accessor.setContentType(MimeTypeUtils.TEXT_PLAIN);

		assertEquals("headers={contentType=text/plain} payload=p", accessor.getShortLogMessage("p"));
		assertEquals("headers={contentType=text/plain} payload=p", accessor.getShortLogMessage("p".getBytes(StandardCharsets.UTF_8)));
		assertEquals("headers={contentType=text/plain} payload=p", accessor.getShortLogMessage(new Object() {
			@Override
			public String toString() {
				return "p";
			}
		}));

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 80; i++) {
			sb.append("a");
		}
		final String payload = sb.toString() + " > 80";

		String actual = accessor.getShortLogMessage(payload);
		assertEquals("headers={contentType=text/plain} payload=" + sb + "...(truncated)", actual);

		actual = accessor.getShortLogMessage(payload.getBytes(StandardCharsets.UTF_8));
		assertEquals("headers={contentType=text/plain} payload=" + sb + "...(truncated)", actual);

		actual = accessor.getShortLogMessage(new Object() {
			@Override
			public String toString() {
				return payload;
			}
		});
		assertThat(actual, startsWith("headers={contentType=text/plain} payload=" + getClass().getName() + "$"));
	}
