	@Test
	public void fromBytesMessage() throws Exception {
		BytesMessage bytesMessageMock = mock(BytesMessage.class);
		Map<String, String> unmarshalled = Collections.singletonMap("foo", "bar");

		byte[] bytes = "{\"foo\":\"bar\"}".getBytes();
		final ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);

		given(bytesMessageMock.getStringProperty("__typeid__")).willReturn(Object.class.getName());
		given(bytesMessageMock.propertyExists("__encoding__")).willReturn(false);
		given(bytesMessageMock.getBodyLength()).willReturn(new Long(bytes.length));
		given(bytesMessageMock.readBytes(any(byte[].class))).willAnswer(
				new Answer<Integer>() {
					@Override
					public Integer answer(InvocationOnMock invocation) throws Throwable {
						return byteStream.read((byte[]) invocation.getArguments()[0]);
					}
				});

		Object result = converter.fromMessage(bytesMessageMock);
		assertEquals("Invalid result", result, unmarshalled);
	}
