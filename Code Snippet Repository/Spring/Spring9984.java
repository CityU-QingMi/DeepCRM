	@Test
	public void normal() throws URISyntaxException {
		String headerName = "My-Custom-Header";
		String headerValue = "HeaderValue";
		URI url = new URI("http://example.com");
		Integer entity = 42;

		RequestEntity<Object> requestEntity =
				RequestEntity.method(HttpMethod.GET, url)
						.header(headerName, headerValue).body(entity);

		assertNotNull(requestEntity);
		assertEquals(HttpMethod.GET, requestEntity.getMethod());
		assertTrue(requestEntity.getHeaders().containsKey(headerName));
		assertEquals(headerValue, requestEntity.getHeaders().getFirst(headerName));
		assertEquals(entity, requestEntity.getBody());
	}
