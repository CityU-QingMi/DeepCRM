	@Test
	public void resolveResourceMatchingEncoding() {
		Resource resource = Mockito.mock(Resource.class);
		Resource gzResource = Mockito.mock(Resource.class);
		this.cache.put(CachingResourceResolver.RESOLVED_RESOURCE_CACHE_KEY_PREFIX + "bar.css", resource);
		this.cache.put(CachingResourceResolver.RESOLVED_RESOURCE_CACHE_KEY_PREFIX + "bar.css+encoding=gzip", gzResource);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "bar.css");
		assertSame(resource, this.chain.resolveResource(request,"bar.css", this.locations));

		request = new MockHttpServletRequest("GET", "bar.css");
		request.addHeader("Accept-Encoding", "gzip");
		assertSame(gzResource, this.chain.resolveResource(request, "bar.css", this.locations));
	}
