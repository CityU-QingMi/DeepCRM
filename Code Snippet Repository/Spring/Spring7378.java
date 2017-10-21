	@Test
	public void fromMessageUntyped() throws Exception {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		String payload = "{\"bytes\":\"AQI=\",\"array\":[\"Foo\",\"Bar\"],"
				+ "\"number\":42,\"string\":\"Foo\",\"bool\":true,\"fraction\":42.0}";
		Message<?> message = MessageBuilder.withPayload(payload.getBytes(StandardCharsets.UTF_8)).build();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> actual = (HashMap<String, Object>) converter.fromMessage(message, HashMap.class);

		assertEquals("Foo", actual.get("string"));
		assertEquals(42, actual.get("number"));
		assertEquals(42D, (Double) actual.get("fraction"), 0D);
		assertEquals(Arrays.asList("Foo", "Bar"), actual.get("array"));
		assertEquals(Boolean.TRUE, actual.get("bool"));
		assertEquals("AQI=", actual.get("bytes"));
	}
