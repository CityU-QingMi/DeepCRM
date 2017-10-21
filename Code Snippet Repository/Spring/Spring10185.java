	@Test
	public void readWithNoDefaultConstructor() throws Exception {
		String body = "{\"property1\":\"foo\",\"property2\":\"bar\"}";
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(body.getBytes("UTF-8"));
		inputMessage.getHeaders().setContentType(new MediaType("application", "json"));
		try {
			converter.read(BeanWithNoDefaultConstructor.class, inputMessage);
		}
		catch (HttpMessageConversionException ex) {
			assertTrue(ex.getMessage(), ex.getMessage().startsWith("Type definition error:"));
			return;
		}
		fail();
	}
