	@Test
	public void customizeDefaultModulesWithSerializer() throws JsonProcessingException, UnsupportedEncodingException {
		Map<Class<?>, JsonSerializer<?>> serializers = new HashMap<>();
		serializers.put(Integer.class, new CustomIntegerSerializer());

		this.factory.setSerializersByType(serializers);
		this.factory.afterPropertiesSet();
		ObjectMapper objectMapper = this.factory.getObject();

		DateTime dateTime = new DateTime(1322903730000L, DateTimeZone.UTC);
		assertEquals("1322903730000", new String(objectMapper.writeValueAsBytes(dateTime), "UTF-8"));
		assertThat(new String(objectMapper.writeValueAsBytes(new Integer(4)), "UTF-8"), containsString("customid"));
	}
