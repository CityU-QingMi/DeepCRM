	@Test
	public void wellKnownModules() throws JsonProcessingException, UnsupportedEncodingException {
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

		Long timestamp = 1322903730000L;
		DateTime dateTime = new DateTime(timestamp, DateTimeZone.UTC);
		assertEquals(timestamp.toString(), new String(objectMapper.writeValueAsBytes(dateTime), "UTF-8"));

		Path file = Paths.get("foo");
		assertTrue(new String(objectMapper.writeValueAsBytes(file), "UTF-8").endsWith("foo\""));

		Optional<String> optional = Optional.of("test");
		assertEquals("\"test\"", new String(objectMapper.writeValueAsBytes(optional), "UTF-8"));

		// Kotlin module
		IntRange range = new IntRange(1, 3);
		assertEquals("{\"start\":1,\"end\":3}", new String(objectMapper.writeValueAsBytes(range), "UTF-8"));
	}
