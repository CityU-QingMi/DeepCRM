	@Test
	public void write() throws Exception {
		String body = "<root>Hello World</root>";
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();

		Marshaller marshaller = mock(Marshaller.class);
		willDoNothing().given(marshaller).marshal(eq(body), isA(Result.class));

		MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter(marshaller);
		converter.write(body, null, outputMessage);

		assertEquals("Invalid content-type", new MediaType("application", "xml"), outputMessage.getHeaders()
				.getContentType());
	}
