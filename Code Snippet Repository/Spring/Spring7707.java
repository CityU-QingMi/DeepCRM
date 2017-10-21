	@Test
	public void getDetailedLogMessagePayload() {
		MessageHeaderAccessor accessor = new MessageHeaderAccessor();
		accessor.setContentType(MimeTypeUtils.TEXT_PLAIN);

		assertEquals("headers={contentType=text/plain} payload=p", accessor.getDetailedLogMessage("p"));
		assertEquals("headers={contentType=text/plain} payload=p", accessor.getDetailedLogMessage("p".getBytes(StandardCharsets.UTF_8)));
		assertEquals("headers={contentType=text/plain} payload=p", accessor.getDetailedLogMessage(new Object() {
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

		String actual = accessor.getDetailedLogMessage(payload);
		assertEquals("headers={contentType=text/plain} payload=" + sb + " > 80", actual);

		actual = accessor.getDetailedLogMessage(payload.getBytes(StandardCharsets.UTF_8));
		assertEquals("headers={contentType=text/plain} payload=" + sb + " > 80", actual);

		actual = accessor.getDetailedLogMessage(new Object() {
			@Override
			public String toString() {
				return payload;
			}
		});
		assertEquals("headers={contentType=text/plain} payload=" + sb + " > 80", actual);
	}
