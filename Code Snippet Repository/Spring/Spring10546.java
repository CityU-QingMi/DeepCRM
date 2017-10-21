	@Test
	public void ioExceptionWithEmptyQueryString() throws Exception {

		// http://example.com/resource?
		URI uri = new URI("http", "example.com", "/resource", "", null);

		given(converter.canRead(String.class, null)).willReturn(true);
		given(converter.getSupportedMediaTypes()).willReturn(Collections.singletonList(parseMediaType("foo/bar")));
		given(requestFactory.createRequest(uri, HttpMethod.GET)).willReturn(request);
		given(request.getHeaders()).willReturn(new HttpHeaders());
		given(request.execute()).willThrow(new IOException("Socket failure"));

		try {
			template.getForObject(uri, String.class);
			fail("RestClientException expected");
		}
		catch (ResourceAccessException ex) {
			assertEquals("I/O error on GET request for \"http://example.com/resource\": " +
					"Socket failure; nested exception is java.io.IOException: Socket failure",
					ex.getMessage());
		}
	}
