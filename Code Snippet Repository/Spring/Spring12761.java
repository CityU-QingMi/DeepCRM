	@Test
	public void mappedCacheConfigurationOverridesGlobal() throws Exception {
		Properties mappings = new Properties();
		mappings.setProperty("*/*handle.vm", "-1"); // was **/*handle.vm

		WebContentInterceptor interceptor = new WebContentInterceptor();
		interceptor.setCacheSeconds(10);
		interceptor.setCacheMappings(mappings);

		// request.setRequestURI("http://localhost:7070/example/adminhandle.vm");
		request.setRequestURI("example/adminhandle.vm");
		interceptor.preHandle(request, response, null);

		Iterable<String> cacheControlHeaders = response.getHeaders("Cache-Control");
		assertThat(cacheControlHeaders, Matchers.emptyIterable());

		// request.setRequestURI("http://localhost:7070/example/bingo.html");
		request.setRequestURI("example/bingo.html");
		interceptor.preHandle(request, response, null);

		cacheControlHeaders = response.getHeaders("Cache-Control");
		assertThat(cacheControlHeaders, Matchers.hasItem("max-age=10"));
	}
