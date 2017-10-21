	@Test
	public void booleanSetters() {
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
				.featuresToEnable(MapperFeature.DEFAULT_VIEW_INCLUSION,
						DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
						SerializationFeature.INDENT_OUTPUT)
				.featuresToDisable(MapperFeature.AUTO_DETECT_FIELDS,
						MapperFeature.AUTO_DETECT_GETTERS,
						MapperFeature.AUTO_DETECT_SETTERS,
						SerializationFeature.FAIL_ON_EMPTY_BEANS).build();
		assertNotNull(objectMapper);
		assertTrue(objectMapper.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION));
		assertTrue(objectMapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
		assertFalse(objectMapper.isEnabled(MapperFeature.AUTO_DETECT_FIELDS));
		assertFalse(objectMapper.isEnabled(MapperFeature.AUTO_DETECT_GETTERS));
		assertFalse(objectMapper.isEnabled(MapperFeature.AUTO_DETECT_SETTERS));
		assertTrue(objectMapper.isEnabled(SerializationFeature.INDENT_OUTPUT));
		assertFalse(objectMapper.isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS));
	}
