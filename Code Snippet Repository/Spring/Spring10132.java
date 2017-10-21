	@Test
	@SuppressWarnings("")
	public void writeContentNotGettingInputStream() throws Exception {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		Resource resource = mock(Resource.class);
		given(resource.getInputStream()).willThrow(FileNotFoundException.class);
		converter.write(resource, MediaType.APPLICATION_OCTET_STREAM, outputMessage);

		assertEquals(0, outputMessage.getHeaders().getContentLength());
	}
