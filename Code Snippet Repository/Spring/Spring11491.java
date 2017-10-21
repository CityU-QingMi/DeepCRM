	@Test
	public void uriBuilder() throws Exception {
		URI uri = new URI("http", "localhost", "/path", "a=1", null);
		MockServerHttpRequest mockRequest = MockServerHttpRequest.method(HttpMethod.GET, uri).build();
		DefaultServerRequest request = new DefaultServerRequest(MockServerWebExchange.from(mockRequest), messageReaders);


		URI result = request.uriBuilder().build();
		assertEquals("http", result.getScheme());
		assertEquals("localhost", result.getHost());
		assertEquals(-1, result.getPort());
		assertEquals("/path", result.getPath());
		assertEquals("a=1", result.getQuery());
	}
