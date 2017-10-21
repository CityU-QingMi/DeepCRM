	@Test
	public void handleReturnValueString() throws Exception {
		List<HttpMessageConverter<?>>converters = new ArrayList<>();
		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());

		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(converters);
		processor.handleReturnValue("Foo", returnTypeString, container, request);

		assertEquals("text/plain;charset=ISO-8859-1", servletResponse.getHeader("Content-Type"));
		assertEquals("Foo", servletResponse.getContentAsString());
	}
