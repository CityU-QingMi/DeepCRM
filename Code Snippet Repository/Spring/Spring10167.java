	@Test
	public void filters() throws JsonProcessingException {
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
				.filters(new SimpleFilterProvider().setFailOnUnknownId(false)).build();
		JacksonFilteredBean bean = new JacksonFilteredBean("value1", "value2");
		String output = objectMapper.writeValueAsString(bean);
		assertThat(output, containsString("value1"));
		assertThat(output, containsString("value2"));

		objectMapper = Jackson2ObjectMapperBuilder.json().filters((new SimpleFilterProvider().setFailOnUnknownId(false)
				.setDefaultFilter(SimpleBeanPropertyFilter.serializeAllExcept("property2")))).build();
		output = objectMapper.writeValueAsString(bean);
		assertThat(output, containsString("value1"));
		assertThat(output, not(containsString("value2")));
	}
