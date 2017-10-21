	@Test
	public void shouldReadInputStreamResource() throws IOException {
		try (InputStream body = getClass().getResourceAsStream("logo.jpg") ) {
			MockHttpInputMessage inputMessage = new MockHttpInputMessage(body);
			inputMessage.getHeaders().setContentType(MediaType.IMAGE_JPEG);
			inputMessage.getHeaders().setContentDisposition(
					ContentDisposition.builder("attachment").filename("yourlogo.jpg").build());
			Resource actualResource = converter.read(InputStreamResource.class, inputMessage);
			assertThat(actualResource, instanceOf(InputStreamResource.class));
			assertThat(actualResource.getInputStream(), is(body));
			assertEquals("yourlogo.jpg", actualResource.getFilename());
		}
	}
