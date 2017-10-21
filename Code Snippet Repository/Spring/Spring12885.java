	private void testVaryHeader(String[] entityValues, String[] existingValues, String[] expected) throws Exception {
		ResponseEntity<String> returnValue = ResponseEntity.ok().varyBy(entityValues).body("Foo");
		for (String value : existingValues) {
			servletResponse.addHeader("Vary", value);
		}
		initStringMessageConversion(MediaType.TEXT_PLAIN);
		processor.handleReturnValue(returnValue, returnTypeResponseEntity, mavContainer, webRequest);

		assertTrue(mavContainer.isRequestHandled());
		assertEquals(Arrays.asList(expected), servletResponse.getHeaders("Vary"));
		verify(stringHttpMessageConverter).write(eq("Foo"), eq(MediaType.TEXT_PLAIN), isA(HttpOutputMessage.class));
	}
