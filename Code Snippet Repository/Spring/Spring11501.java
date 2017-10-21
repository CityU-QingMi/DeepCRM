	@Test
	public void cookies() {
		HttpCookie cookie = new HttpCookie("foo", "bar");
		MockServerHttpRequest mockRequest = MockServerHttpRequest.method(HttpMethod.GET, "http://example.com").
				cookie(cookie).build();
		MockServerWebExchange exchange = MockServerWebExchange.from(mockRequest);

		DefaultServerRequest request = new DefaultServerRequest(exchange, messageReaders);

		MultiValueMap<String, HttpCookie> expected = new LinkedMultiValueMap<>();
		expected.add("foo", cookie);

		assertEquals(expected, request.cookies());

	}
