	@Test
	public void write() throws IOException {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		byte[] body = new byte[]{0x1, 0x2};
		converter.write(body, null, outputMessage);
		assertArrayEquals("Invalid result", body, outputMessage.getBodyAsBytes());
		assertEquals("Invalid content-type", new MediaType("application", "octet-stream"),
				outputMessage.getHeaders().getContentType());
		assertEquals("Invalid content-length", 2, outputMessage.getHeaders().getContentLength());
	}
