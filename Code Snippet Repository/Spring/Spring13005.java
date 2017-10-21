	@Test(expected = HttpMediaTypeNotSupportedException.class)
	public void resolveArgumentCannotRead() throws Exception {
		MediaType contentType = MediaType.TEXT_PLAIN;
		servletRequest.addHeader("Content-Type", contentType.toString());
		servletRequest.setContent("payload".getBytes(StandardCharsets.UTF_8));

		given(stringMessageConverter.canRead(String.class, contentType)).willReturn(false);

		processor.resolveArgument(paramRequestBodyString, mavContainer, webRequest, null);
	}
