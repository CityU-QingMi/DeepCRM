	@Test
	@SuppressWarnings("")
	public void writeContentInputStreamThrowingNullPointerException() throws Exception {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		Resource resource = mock(Resource.class);
		InputStream in = mock(InputStream.class);
		given(resource.getInputStream()).willReturn(in);
		given(in.read(any())).willThrow(NullPointerException.class);
		converter.write(resource, MediaType.APPLICATION_OCTET_STREAM, outputMessage);

		assertEquals(0, outputMessage.getHeaders().getContentLength());
	}
