	@Test
	public void createHttpUriRequest() throws Exception {
		URI uri = new URI("http://example.com");
		testRequestBodyAllowed(uri, HttpMethod.GET, false);
		testRequestBodyAllowed(uri, HttpMethod.HEAD, false);
		testRequestBodyAllowed(uri, HttpMethod.OPTIONS, false);
		testRequestBodyAllowed(uri, HttpMethod.TRACE, false);
		testRequestBodyAllowed(uri, HttpMethod.PUT, true);
		testRequestBodyAllowed(uri, HttpMethod.POST, true);
		testRequestBodyAllowed(uri, HttpMethod.PATCH, true);
		testRequestBodyAllowed(uri, HttpMethod.DELETE, true);

	}
