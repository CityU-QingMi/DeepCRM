	@Test
	public void shouldResolveHttpEntityArgument() throws Exception {
		String body = "Foo";

		MediaType contentType = MediaType.TEXT_PLAIN;
		servletRequest.addHeader("Content-Type", contentType.toString());
		servletRequest.setContent(body.getBytes(StandardCharsets.UTF_8));

		given(stringHttpMessageConverter.canRead(String.class, contentType)).willReturn(true);
		given(stringHttpMessageConverter.read(eq(String.class), isA(HttpInputMessage.class))).willReturn(body);

		Object result = processor.resolveArgument(paramHttpEntity, mavContainer, webRequest, null);

		assertTrue(result instanceof HttpEntity);
		assertFalse("The requestHandled flag shouldn't change", mavContainer.isRequestHandled());
		assertEquals("Invalid argument", body, ((HttpEntity<?>) result).getBody());
	}
