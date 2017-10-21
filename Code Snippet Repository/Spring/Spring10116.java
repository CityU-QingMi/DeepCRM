	@Test
	public void read() throws IOException {
		Resource logo = new ClassPathResource("logo.jpg", BufferedImageHttpMessageConverterTests.class);
		byte[] body = FileCopyUtils.copyToByteArray(logo.getInputStream());
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(body);
		inputMessage.getHeaders().setContentType(new MediaType("image", "jpeg"));
		BufferedImage result = converter.read(BufferedImage.class, inputMessage);
		assertEquals("Invalid height", 500, result.getHeight());
		assertEquals("Invalid width", 750, result.getWidth());
	}
