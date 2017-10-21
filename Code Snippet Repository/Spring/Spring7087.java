	@Test
	public void toBytesMessage() throws Exception {
		BytesMessage bytesMessageMock = mock(BytesMessage.class);
		Date toBeMarshalled = new Date();

		given(sessionMock.createBytesMessage()).willReturn(bytesMessageMock);

		converter.toMessage(toBeMarshalled, sessionMock);

		verify(bytesMessageMock).setStringProperty("__encoding__", "UTF-8");
		verify(bytesMessageMock).setStringProperty("__typeid__", Date.class.getName());
		verify(bytesMessageMock).writeBytes(isA(byte[].class));
	}
