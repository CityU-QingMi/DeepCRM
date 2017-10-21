	@Test
	public void xForwardedRequest() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("http://example.com/path")
				.header("X-Forwarded-Host", "84.198.58.199")
				.header("X-Forwarded-Port", "443")
				.header("X-Forwarded-Proto", "https")
				.build());

		this.filter.filter(exchange, this.filterChain).block(Duration.ZERO);

		URI uri = this.filterChain.uri;
		assertEquals(new URI("https://84.198.58.199/path"), uri);
	}
