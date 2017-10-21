	@Test
	public void writeDefaultCharset() throws IOException {
		String body = "H\u00e9llo W\u00f6rld";
		this.converter.write(body, null, this.outputMessage);

		HttpHeaders headers = this.outputMessage.getHeaders();
		assertEquals(body, this.outputMessage.getBodyAsString(StandardCharsets.ISO_8859_1));
		assertEquals(new MediaType("text", "plain", StandardCharsets.ISO_8859_1), headers.getContentType());
		assertEquals(body.getBytes(StandardCharsets.ISO_8859_1).length, headers.getContentLength());
		assertFalse(headers.getAcceptCharset().isEmpty());
	}
