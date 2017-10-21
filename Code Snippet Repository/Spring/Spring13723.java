	@Test
	public void renderSimpleBeanWithFilters() throws Exception {
		TestSimpleBeanFiltered bean = new TestSimpleBeanFiltered();
		bean.setProperty1("value");
		bean.setProperty2("value");
		Map<String, Object> model = new HashMap<>();
		model.put("bindingResult", mock(BindingResult.class, "binding_result"));
		model.put("foo", bean);
		FilterProvider filters = new SimpleFilterProvider().addFilter("myJacksonFilter",
				SimpleBeanPropertyFilter.serializeAllExcept("property2"));
		model.put(FilterProvider.class.getName(), filters);

		view.setUpdateContentLength(true);
		view.render(model, request, response);

		String content = response.getContentAsString();
		assertTrue(content.length() > 0);
		assertEquals(content.length(), response.getContentLength());
		assertThat(content, containsString("\"property1\":\"value\""));
		assertThat(content, not(containsString("\"property2\":\"value\"")));
		assertFalse(content.contains(FilterProvider.class.getName()));
	}
