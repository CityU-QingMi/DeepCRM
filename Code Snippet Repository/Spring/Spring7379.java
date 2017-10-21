	@Test
	public void toMessage() throws Exception {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		MyBean payload = new MyBean();
		payload.setString("Foo");
		payload.setNumber(42);
		payload.setFraction(42F);
		payload.setArray(new String[]{"Foo", "Bar"});
		payload.setBool(true);
		payload.setBytes(new byte[]{0x1, 0x2});

		Message<?> message = converter.toMessage(payload, null);
		String actual = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);

		assertTrue(actual.contains("\"string\":\"Foo\""));
		assertTrue(actual.contains("\"number\":42"));
		assertTrue(actual.contains("fraction\":42.0"));
		assertTrue(actual.contains("\"array\":[\"Foo\",\"Bar\"]"));
		assertTrue(actual.contains("\"bool\":true"));
		assertTrue(actual.contains("\"bytes\":\"AQI=\""));
		assertEquals("Invalid content-type", new MimeType("application", "json", StandardCharsets.UTF_8),
				message.getHeaders().get(MessageHeaders.CONTENT_TYPE, MimeType.class));
	}
