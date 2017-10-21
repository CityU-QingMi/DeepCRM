	@SuppressWarnings("")
	@Test
	public void http10CachingConfigAndSpecificMapping() throws Exception {
		WebContentInterceptor interceptor = new WebContentInterceptor();
		interceptor.setCacheSeconds(0);
		interceptor.setUseExpiresHeader(true);
		interceptor.setAlwaysMustRevalidate(true);
		Properties mappings = new Properties();
		mappings.setProperty("*/*.cache.html", "10"); // was **/*.cache.html
		interceptor.setCacheMappings(mappings);

		// request.setRequestURI("http://example.org/foo/page.html");
		request.setRequestURI("foo/page.html");
		interceptor.preHandle(request, response, null);

		Iterable<String> expiresHeaders = response.getHeaders("Expires");
		assertThat(expiresHeaders, Matchers.iterableWithSize(1));
		Iterable<String> cacheControlHeaders = response.getHeaders("Cache-Control");
		assertThat(cacheControlHeaders, Matchers.contains("no-cache", "no-store"));
		Iterable<String> pragmaHeaders = response.getHeaders("Pragma");
		assertThat(pragmaHeaders, Matchers.contains("no-cache"));

		// request.setRequestURI("http://example.org/page.cache.html");
		request = new MockHttpServletRequest("GET", "foo/page.cache.html");
		response = new MockHttpServletResponse();
		interceptor.preHandle(request, response, null);

		expiresHeaders = response.getHeaders("Expires");
		assertThat(expiresHeaders, Matchers.iterableWithSize(1));
		cacheControlHeaders = response.getHeaders("Cache-Control");
		assertThat(cacheControlHeaders, Matchers.contains("max-age=10, must-revalidate"));
	}
