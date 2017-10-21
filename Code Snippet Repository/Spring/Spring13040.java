	@Test
	public void resolveArgumentRequiredNoContentDefaultValue() throws Exception {
		this.servletRequest.setContent(new byte[0]);
		this.servletRequest.setContentType("text/plain");
		List<HttpMessageConverter<?>> converters = Collections.singletonList(new StringHttpMessageConverter());
		List<Object> advice = Collections.singletonList(new EmptyRequestBodyAdvice());
		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(converters, advice);
		String arg = (String) processor.resolveArgument(paramString, container, request, factory);
		assertNotNull(arg);
		assertEquals("default value for empty body", arg);
	}
