	@Test
	public void getStaticResourceUrlRequestWithQueryOrHash() {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").build());

		String url = "/resources/foo.css?foo=bar&url=http://example.org";
		String resolvedUrl = this.urlProvider.getForUriString(url, exchange).block(Duration.ofSeconds(5));
		assertEquals(url, resolvedUrl);

		url = "/resources/foo.css#hash";
		resolvedUrl = this.urlProvider.getForUriString(url, exchange).block(Duration.ofSeconds(5));
		assertEquals(url, resolvedUrl);
	}
