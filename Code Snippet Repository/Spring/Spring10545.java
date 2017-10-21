	@Test
	public void ioException() throws Exception {
		String url = "http://example.com/resource?access_token=123";

		given(converter.canRead(String.class, null)).willReturn(true);
		MediaType mediaType = new MediaType("foo", "bar");
		given(converter.getSupportedMediaTypes()).willReturn(Collections.singletonList(mediaType));
		given(requestFactory.createRequest(new URI(url), HttpMethod.GET)).willReturn(request);
		given(request.getHeaders()).willReturn(new HttpHeaders());
		given(request.execute()).willThrow(new IOException("Socket failure"));

		try {
			template.getForObject(url, String.class);
			fail("RestClientException expected");
		}
		catch (ResourceAccessException ex) {
			assertEquals("I/O error on GET request for \"http://example.com/resource\": " +
					"Socket failure; nested exception is java.io.IOException: Socket failure",
					ex.getMessage());
		}
	}
