	@Test
	public void read() throws Exception {
		String body = "<root>Hello World</root>";
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(body.getBytes("UTF-8"));

		Unmarshaller unmarshaller = mock(Unmarshaller.class);
		given(unmarshaller.unmarshal(isA(StreamSource.class))).willReturn(body);

		MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
		converter.setUnmarshaller(unmarshaller);

		String result = (String) converter.read(Object.class, inputMessage);
		assertEquals("Invalid result", body, result);
	}
