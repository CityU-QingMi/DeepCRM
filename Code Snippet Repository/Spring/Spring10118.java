	@Test
	public void writeDefaultContentType() throws IOException {
		Resource logo = new ClassPathResource("logo.jpg", BufferedImageHttpMessageConverterTests.class);
		MediaType contentType = new MediaType("image", "png");
		converter.setDefaultContentType(contentType);
		BufferedImage body = ImageIO.read(logo.getFile());
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		converter.write(body, new MediaType("*", "*"), outputMessage);
		assertEquals("Invalid content type", contentType, outputMessage.getWrittenHeaders().getContentType());
		assertTrue("Invalid size", outputMessage.getBodyAsBytes().length > 0);
		BufferedImage result = ImageIO.read(new ByteArrayInputStream(outputMessage.getBodyAsBytes()));
		assertEquals("Invalid height", 500, result.getHeight());
		assertEquals("Invalid width", 750, result.getWidth());
	}
