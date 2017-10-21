	@Test
	public void shouldFailResolvingWhenConverterCannotRead() throws Exception {
		MediaType contentType = MediaType.TEXT_PLAIN;
		servletRequest.setMethod("POST");
		servletRequest.addHeader("Content-Type", contentType.toString());

		given(stringHttpMessageConverter.getSupportedMediaTypes()).willReturn(Collections.singletonList(contentType));
		given(stringHttpMessageConverter.canRead(String.class, contentType)).willReturn(false);

		this.thrown.expect(HttpMediaTypeNotSupportedException.class);
		processor.resolveArgument(paramHttpEntity, mavContainer, webRequest, null);
	}
