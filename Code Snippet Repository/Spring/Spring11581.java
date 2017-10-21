	@Test
	public void resolveResourceNoAcceptEncodingInCacheKey() {
		String file = "bar.css";
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get(file).build());

		Resource expected = this.chain.resolveResource(exchange, file, this.locations).block(TIMEOUT);
		String cacheKey = CachingResourceResolver.RESOLVED_RESOURCE_CACHE_KEY_PREFIX + file;

		assertEquals(expected, this.cache.get(cacheKey).get());
	}
