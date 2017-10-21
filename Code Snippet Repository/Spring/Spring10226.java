	@Test
	public void readWithMarshallingFailureException() throws Exception {
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(new byte[0]);
		UnmarshallingFailureException ex = new UnmarshallingFailureException("forced");

		Unmarshaller unmarshaller = mock(Unmarshaller.class);
		given(unmarshaller.unmarshal(isA(StreamSource.class))).willThrow(ex);

		MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
		converter.setUnmarshaller(unmarshaller);

		try {
			converter.read(Object.class, inputMessage);
			fail("HttpMessageNotReadableException should be thrown");
		}
		catch (HttpMessageNotReadableException e) {
			assertTrue("Invalid exception hierarchy", e.getCause() == ex);
		}
	}
