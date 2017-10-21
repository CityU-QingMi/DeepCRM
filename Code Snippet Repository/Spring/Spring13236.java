	@Test
	public void resolveResourceAcceptEncodingInCacheKey() {
		String file = "bar.css";

		MockHttpServletRequest request = new MockHttpServletRequest("GET", file);
		request.addHeader("Accept-Encoding", "gzip");
		Resource expected = this.chain.resolveResource(request, file, this.locations);
		String cacheKey = CachingResourceResolver.RESOLVED_RESOURCE_CACHE_KEY_PREFIX + file + "+encoding=gzip";

		assertEquals(expected, this.cache.get(cacheKey).get());
	}
