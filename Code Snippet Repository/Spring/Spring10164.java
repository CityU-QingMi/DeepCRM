	@Test
	public void deserializerByType() throws JsonMappingException {
		JsonDeserializer<Date> deserializer = new DateDeserializers.DateDeserializer();
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
				.modules(new ArrayList<>())  // Disable well-known modules detection
				.deserializerByType(Date.class, deserializer).build();
		assertTrue(getDeserializerFactoryConfig(objectMapper).hasDeserializers());
		Deserializers deserializers = getDeserializerFactoryConfig(objectMapper).deserializers().iterator().next();
		assertSame(deserializer, deserializers.findBeanDeserializer(SimpleType.construct(Date.class), null, null));
	}
