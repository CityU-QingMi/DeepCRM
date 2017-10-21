	@Test
	public void propertiesShortcut() {
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().autoDetectFields(false)
				.defaultViewInclusion(true).failOnUnknownProperties(true).failOnEmptyBeans(false)
				.autoDetectGettersSetters(false).indentOutput(true).build();
		assertNotNull(objectMapper);
		assertTrue(objectMapper.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION));
		assertTrue(objectMapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
		assertFalse(objectMapper.isEnabled(MapperFeature.AUTO_DETECT_FIELDS));
		assertFalse(objectMapper.isEnabled(MapperFeature.AUTO_DETECT_GETTERS));
		assertFalse(objectMapper.isEnabled(MapperFeature.AUTO_DETECT_IS_GETTERS));
		assertFalse(objectMapper.isEnabled(MapperFeature.AUTO_DETECT_SETTERS));
		assertTrue(objectMapper.isEnabled(SerializationFeature.INDENT_OUTPUT));
		assertFalse(objectMapper.isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS));
	}
