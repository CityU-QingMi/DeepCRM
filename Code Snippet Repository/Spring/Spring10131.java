	@Test
	public void shouldWriteImageResource() throws IOException {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		Resource body = new ClassPathResource("logo.jpg", getClass());
		converter.write(body, null, outputMessage);

		assertEquals("Invalid content-type", MediaType.IMAGE_JPEG,
				outputMessage.getHeaders().getContentType());
		assertEquals("Invalid content-length", body.getFile().length(), outputMessage.getHeaders().getContentLength());
	}
