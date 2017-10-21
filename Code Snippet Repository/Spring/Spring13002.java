	@Test
	public void resolveArgument() throws Exception {
		MediaType contentType = MediaType.TEXT_PLAIN;
		servletRequest.addHeader("Content-Type", contentType.toString());

		String body = "Foo";
		servletRequest.setContent(body.getBytes(StandardCharsets.UTF_8));

		given(stringMessageConverter.canRead(String.class, contentType)).willReturn(true);
		given(stringMessageConverter.read(eq(String.class), isA(HttpInputMessage.class))).willReturn(body);

		Object result = processor.resolveArgument(paramRequestBodyString, mavContainer, webRequest, new ValidatingBinderFactory());

		assertEquals("Invalid argument", body, result);
		assertFalse("The requestHandled flag shouldn't change", mavContainer.isRequestHandled());
	}
