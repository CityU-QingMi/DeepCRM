	@Test
	public void writeContentNotClosingInputStream() throws Exception {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		Resource resource = mock(Resource.class);
		InputStream inputStream = mock(InputStream.class);
		given(resource.getInputStream()).willReturn(inputStream);
		given(inputStream.read(any())).willReturn(-1);
		doThrow(new NullPointerException()).when(inputStream).close();
		converter.write(resource, MediaType.APPLICATION_OCTET_STREAM, outputMessage);

		assertEquals(0, outputMessage.getHeaders().getContentLength());
	}
