	@Test
	public void emptyCacheConfiguration() throws Exception {
		WebContentInterceptor interceptor = new WebContentInterceptor();
		interceptor.setCacheSeconds(-1);

		interceptor.preHandle(request, response, null);

		Iterable<String> expiresHeaders = response.getHeaders("Expires");
		assertThat(expiresHeaders, Matchers.emptyIterable());
		Iterable<String> cacheControlHeaders = response.getHeaders("Cache-Control");
		assertThat(cacheControlHeaders, Matchers.emptyIterable());
	}
