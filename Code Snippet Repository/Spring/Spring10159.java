	@Test
	public void defaultProperties() {
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
		assertNotNull(objectMapper);
		assertFalse(objectMapper.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION));
		assertFalse(objectMapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
		assertTrue(objectMapper.isEnabled(MapperFeature.AUTO_DETECT_FIELDS));
		assertTrue(objectMapper.isEnabled(MapperFeature.AUTO_DETECT_GETTERS));
		assertTrue(objectMapper.isEnabled(MapperFeature.AUTO_DETECT_IS_GETTERS));
		assertTrue(objectMapper.isEnabled(MapperFeature.AUTO_DETECT_SETTERS));
		assertFalse(objectMapper.isEnabled(SerializationFeature.INDENT_OUTPUT));
		assertTrue(objectMapper.isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS));
	}
