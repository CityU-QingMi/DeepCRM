	@Test
	public void setFilters() throws JsonProcessingException {
		this.factory.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
		this.factory.afterPropertiesSet();
		ObjectMapper objectMapper = this.factory.getObject();

		JacksonFilteredBean bean = new JacksonFilteredBean("value1", "value2");
		String output = objectMapper.writeValueAsString(bean);
		assertThat(output, containsString("value1"));
		assertThat(output, containsString("value2"));
	}
