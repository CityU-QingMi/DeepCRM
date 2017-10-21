	@Test(expected = TypeMismatchException.class)
	public void readWithTypeMismatchException() throws Exception {
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(new byte[0]);

		Marshaller marshaller = mock(Marshaller.class);
		Unmarshaller unmarshaller = mock(Unmarshaller.class);
		given(unmarshaller.unmarshal(isA(StreamSource.class))).willReturn(Integer.valueOf(3));

		MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter(marshaller, unmarshaller);
		converter.read(String.class, inputMessage);
	}
