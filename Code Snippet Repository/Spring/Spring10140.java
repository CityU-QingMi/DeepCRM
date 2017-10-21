	@Test
	public void writeUTF8() throws IOException {
		String body = "H\u00e9llo W\u00f6rld";
		this.converter.write(body, TEXT_PLAIN_UTF_8, this.outputMessage);

		HttpHeaders headers = this.outputMessage.getHeaders();
		assertEquals(body, this.outputMessage.getBodyAsString(StandardCharsets.UTF_8));
		assertEquals(TEXT_PLAIN_UTF_8, headers.getContentType());
		assertEquals(body.getBytes(StandardCharsets.UTF_8).length, headers.getContentLength());
		assertFalse(headers.getAcceptCharset().isEmpty());
	}
