	@Test
	public void resolveGenericArgument() throws Exception {
		String content = "[{\"name\" : \"Jad\"}, {\"name\" : \"Robert\"}]";
		this.servletRequest.setContent(content.getBytes("UTF-8"));
		this.servletRequest.setContentType("application/json");

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());
		HttpEntityMethodProcessor processor = new HttpEntityMethodProcessor(converters);

		@SuppressWarnings("unchecked")
		HttpEntity<List<SimpleBean>> result = (HttpEntity<List<SimpleBean>>) processor.resolveArgument(
				paramList, mavContainer, webRequest, binderFactory);

		assertNotNull(result);
		assertEquals("Jad", result.getBody().get(0).getName());
		assertEquals("Robert", result.getBody().get(1).getName());
	}
