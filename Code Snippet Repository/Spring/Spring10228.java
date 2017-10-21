	@Test
	public void writeWithMarshallingFailureException() throws Exception {
		String body = "<root>Hello World</root>";
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		MarshallingFailureException ex = new MarshallingFailureException("forced");

		Marshaller marshaller = mock(Marshaller.class);
		willThrow(ex).given(marshaller).marshal(eq(body), isA(Result.class));

		try {
			MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter(marshaller);
			converter.write(body, null, outputMessage);
			fail("HttpMessageNotWritableException should be thrown");
		}
		catch (HttpMessageNotWritableException e) {
			assertTrue("Invalid exception hierarchy", e.getCause() == ex);
		}
	}
