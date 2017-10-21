	@Test
	public void requestUriWithForwardedPrefixTrailingSlash() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("http://example.com/path")
				.header("X-Forwarded-Prefix", "/prefix/")
				.build());

		this.filter.filter(exchange, this.filterChain).block(Duration.ZERO);

		URI uri = this.filterChain.uri;
		assertEquals(new URI("http://example.com/prefix/path"), uri);
	}
