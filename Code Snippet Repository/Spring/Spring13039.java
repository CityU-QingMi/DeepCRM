	@Test
	public void resolveArgumentClassString() throws Exception {
		String content = "foobarbaz";
		this.servletRequest.setContent(content.getBytes("UTF-8"));
		this.servletRequest.setContentType("application/json");

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new StringHttpMessageConverter());
		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(converters);

		String result = (String) processor.resolveArgument(
				paramString, container, request, factory);

		assertNotNull(result);
		assertEquals("foobarbaz", result);
	}
