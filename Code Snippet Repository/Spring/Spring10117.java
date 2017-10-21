	@Test
	public void write() throws IOException {
		Resource logo = new ClassPathResource("logo.jpg", BufferedImageHttpMessageConverterTests.class);
		BufferedImage body = ImageIO.read(logo.getFile());
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		MediaType contentType = new MediaType("image", "png");
		converter.write(body, contentType, outputMessage);
		assertEquals("Invalid content type", contentType, outputMessage.getWrittenHeaders().getContentType());
		assertTrue("Invalid size", outputMessage.getBodyAsBytes().length > 0);
		BufferedImage result = ImageIO.read(new ByteArrayInputStream(outputMessage.getBodyAsBytes()));
		assertEquals("Invalid height", 500, result.getHeight());
		assertEquals("Invalid width", 750, result.getWidth());
	}
