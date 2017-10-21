	@Test
	public void forwardedRequest() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("http://example.com/path")
				.header("Forwarded", "host=84.198.58.199;proto=https")

				.build());

		this.filter.filter(exchange, this.filterChain).block(Duration.ZERO);

		URI uri = this.filterChain.uri;
		assertEquals(new URI("https://84.198.58.199/path"), uri);
	}
