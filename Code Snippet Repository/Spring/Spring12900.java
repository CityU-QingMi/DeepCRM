	@Test
	public void handleReturnValueCharSequence() throws Exception {
		List<HttpMessageConverter<?>>converters = new ArrayList<>();
		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());

		Method method = getClass().getDeclaredMethod("handle");
		MethodParameter returnType = new MethodParameter(method, -1);
		ResponseEntity<StringBuilder> returnValue = ResponseEntity.ok(new StringBuilder("Foo"));

		HttpEntityMethodProcessor processor = new HttpEntityMethodProcessor(converters);
		processor.handleReturnValue(returnValue, returnType, mavContainer, webRequest);

		assertEquals("text/plain;charset=ISO-8859-1", servletResponse.getHeader("Content-Type"));
		assertEquals("Foo", servletResponse.getContentAsString());
	}
