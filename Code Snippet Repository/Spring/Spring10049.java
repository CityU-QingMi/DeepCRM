	@Test
	public void useNegotiatedMediaTypeCharset() throws Exception {

		MediaType negotiatedMediaType = new MediaType("text", "html", ISO_8859_1);

		HttpMessageWriter<String> writer = getWriter(TEXT_PLAIN_UTF_8, TEXT_HTML);
		writer.write(Mono.just("body"), forClass(String.class), negotiatedMediaType, this.response, NO_HINTS);

		assertEquals(negotiatedMediaType, this.response.getHeaders().getContentType());
		assertEquals(negotiatedMediaType, this.mediaTypeCaptor.getValue());
	}
