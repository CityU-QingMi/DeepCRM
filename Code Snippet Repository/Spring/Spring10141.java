	@Test
	public void writeOverrideRequestedContentType() throws IOException {
		String body = "H\u00e9llo W\u00f6rld";
		MediaType requestedContentType = new MediaType("text", "html");

		HttpHeaders headers = this.outputMessage.getHeaders();
		headers.setContentType(TEXT_PLAIN_UTF_8);
		this.converter.write(body, requestedContentType, this.outputMessage);

		assertEquals(body, this.outputMessage.getBodyAsString(StandardCharsets.UTF_8));
		assertEquals(TEXT_PLAIN_UTF_8, headers.getContentType());
		assertEquals(body.getBytes(StandardCharsets.UTF_8).length, headers.getContentLength());
		assertFalse(headers.getAcceptCharset().isEmpty());
	}
