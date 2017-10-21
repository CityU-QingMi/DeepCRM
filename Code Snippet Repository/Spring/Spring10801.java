	@Test
	public void removeOnly() {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/")
				.header("Forwarded", "for=192.0.2.60;proto=http;by=203.0.113.43")
				.header("X-Forwarded-Host", "example.com")
				.header("X-Forwarded-Port", "8080")
				.header("X-Forwarded-Proto", "http")
				.header("X-Forwarded-Prefix", "prefix")
				.build());

		this.filter.setRemoveOnly(true);
		this.filter.filter(exchange, this.filterChain).block(Duration.ZERO);

		HttpHeaders result = this.filterChain.getHeaders();
		assertNotNull(result);
		assertFalse(result.containsKey("Forwarded"));
		assertFalse(result.containsKey("X-Forwarded-Host"));
		assertFalse(result.containsKey("X-Forwarded-Port"));
		assertFalse(result.containsKey("X-Forwarded-Proto"));
		assertFalse(result.containsKey("X-Forwarded-Prefix"));
	}
