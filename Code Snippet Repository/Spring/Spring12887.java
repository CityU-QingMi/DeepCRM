	@Test
	public void shouldResolveRequestEntityArgument() throws Exception {
		String body = "Foo";

		MediaType contentType = MediaType.TEXT_PLAIN;
		servletRequest.addHeader("Content-Type", contentType.toString());
		servletRequest.setMethod("GET");
		servletRequest.setServerName("www.example.com");
		servletRequest.setServerPort(80);
		servletRequest.setRequestURI("/path");
		servletRequest.setContent(body.getBytes(StandardCharsets.UTF_8));

		given(stringHttpMessageConverter.canRead(String.class, contentType)).willReturn(true);
		given(stringHttpMessageConverter.read(eq(String.class), isA(HttpInputMessage.class))).willReturn(body);

		Object result = processor.resolveArgument(paramRequestEntity, mavContainer, webRequest, null);

		assertTrue(result instanceof RequestEntity);
		assertFalse("The requestHandled flag shouldn't change", mavContainer.isRequestHandled());
		RequestEntity<?> requestEntity = (RequestEntity<?>) result;
		assertEquals("Invalid method", HttpMethod.GET, requestEntity.getMethod());
		// using default port (which is 80), so do not need to append the port (-1 means ignore)
		assertEquals("Invalid url", new URI("http", null, "www.example.com", -1, "/path", null, null), requestEntity.getUrl());
		assertEquals("Invalid argument", body, requestEntity.getBody());
	}
