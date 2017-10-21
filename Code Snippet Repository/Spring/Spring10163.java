	@Test
	public void serializerByType() {
		JsonSerializer<Number> serializer = new NumberSerializer(Integer.class);
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
				.modules(new ArrayList<>())  // Disable well-known modules detection
				.serializerByType(Boolean.class, serializer).build();
		assertTrue(getSerializerFactoryConfig(objectMapper).hasSerializers());
		Serializers serializers = getSerializerFactoryConfig(objectMapper).serializers().iterator().next();
		assertSame(serializer, serializers.findSerializer(null, SimpleType.construct(Boolean.class), null));
	}
