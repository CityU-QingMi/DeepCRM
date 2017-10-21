	private void testDefaultMediaType(MediaType negotiatedMediaType) {

		this.response = new MockServerHttpResponse();
		this.mediaTypeCaptor = ArgumentCaptor.forClass(MediaType.class);

		MimeType defaultContentType = MimeTypeUtils.TEXT_XML;
		HttpMessageWriter<String> writer = getWriter(defaultContentType);
		writer.write(Mono.just("body"), forClass(String.class), negotiatedMediaType, this.response, NO_HINTS);

		assertEquals(defaultContentType, this.response.getHeaders().getContentType());
		assertEquals(defaultContentType, this.mediaTypeCaptor.getValue());
	}
