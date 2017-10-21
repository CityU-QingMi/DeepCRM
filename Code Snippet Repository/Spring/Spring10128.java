	@Test
	public void shouldReadImageResource() throws IOException {
		byte[] body = FileCopyUtils.copyToByteArray(getClass().getResourceAsStream("logo.jpg"));
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(body);
		inputMessage.getHeaders().setContentType(MediaType.IMAGE_JPEG);
		inputMessage.getHeaders().setContentDisposition(
				ContentDisposition.builder("attachment").filename("yourlogo.jpg").build());
		Resource actualResource = converter.read(Resource.class, inputMessage);
		assertThat(FileCopyUtils.copyToByteArray(actualResource.getInputStream()), is(body));
		assertEquals("yourlogo.jpg", actualResource.getFilename());
	}
