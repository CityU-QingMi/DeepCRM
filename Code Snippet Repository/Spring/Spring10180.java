	@Test
	public void filters() throws Exception {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		JacksonFilteredBean bean = new JacksonFilteredBean();
		bean.setProperty1("value");
		bean.setProperty2("value");

		MappingJacksonValue jacksonValue = new MappingJacksonValue(bean);
		FilterProvider filters = new SimpleFilterProvider().addFilter("myJacksonFilter",
				SimpleBeanPropertyFilter.serializeAllExcept("property2"));
		jacksonValue.setFilters(filters);
		this.converter.writeInternal(jacksonValue, null, outputMessage);

		String result = outputMessage.getBodyAsString(StandardCharsets.UTF_8);
		assertThat(result, containsString("\"property1\":\"value\""));
		assertThat(result, not(containsString("\"property2\":\"value\"")));
	}
