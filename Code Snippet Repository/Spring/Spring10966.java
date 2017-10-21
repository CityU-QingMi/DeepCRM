	@Test
	public void encodingValuesOnlySpr14147() throws Exception {
		DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
		factory.setEncodingMode(EncodingMode.VALUES_ONLY);
		factory.setDefaultUriVariables(singletonMap("host", "www.example.com"));
		UriBuilder uriBuilder = factory.uriString("http://{host}/user/{userId}/dashboard");

		assertEquals("http://www.example.com/user/john%3Bdoe/dashboard",
				uriBuilder.build(singletonMap("userId", "john;doe")).toString());
	}
