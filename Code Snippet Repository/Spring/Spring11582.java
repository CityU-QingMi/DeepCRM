	@Test
	public void resolveResourceMatchingEncoding() {
		Resource resource = Mockito.mock(Resource.class);
		Resource gzResource = Mockito.mock(Resource.class);
		this.cache.put(CachingResourceResolver.RESOLVED_RESOURCE_CACHE_KEY_PREFIX + "bar.css", resource);
		this.cache.put(CachingResourceResolver.RESOLVED_RESOURCE_CACHE_KEY_PREFIX + "bar.css+encoding=gzip", gzResource);

		String file = "bar.css";
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get(file).build());
		assertSame(resource, this.chain.resolveResource(exchange, file, this.locations).block(TIMEOUT));

		MockServerHttpRequest request = MockServerHttpRequest.get(file).header("Accept-Encoding", "gzip").build();
		exchange = MockServerWebExchange.from(request);
		assertSame(gzResource, this.chain.resolveResource(exchange, file, this.locations).block(TIMEOUT));
	}
