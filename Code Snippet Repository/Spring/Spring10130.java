	@Test
	public void shouldNotReadInputStreamResource() throws IOException {
		ResourceHttpMessageConverter noStreamConverter = new ResourceHttpMessageConverter(false);
		try (InputStream body = getClass().getResourceAsStream("logo.jpg") ) {
			this.thrown.expect(IllegalStateException.class);
			MockHttpInputMessage inputMessage = new MockHttpInputMessage(body);
			inputMessage.getHeaders().setContentType(MediaType.IMAGE_JPEG);
			noStreamConverter.read(InputStreamResource.class, inputMessage);
		}
	}
