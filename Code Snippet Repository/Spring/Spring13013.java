	@Test
	public void handleReturnValueCharSequence() throws Exception {
		List<HttpMessageConverter<?>>converters = new ArrayList<>();
		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());

		Method method = ResponseBodyController.class.getMethod("handleWithCharSequence");
		MethodParameter returnType = new MethodParameter(method, -1);

		RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(converters);
		processor.handleReturnValue(new StringBuilder("Foo"), returnType, container, request);

		assertEquals("text/plain;charset=ISO-8859-1", servletResponse.getHeader("Content-Type"));
		assertEquals("Foo", servletResponse.getContentAsString());
	}
