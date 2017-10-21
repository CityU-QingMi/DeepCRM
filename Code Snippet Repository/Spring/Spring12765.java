	@SuppressWarnings("")
	@Test
	public void http10CachingConfigAndPragmaHeader() throws Exception {
		WebContentInterceptor interceptor = new WebContentInterceptor();
		interceptor.setCacheSeconds(10);
		interceptor.setAlwaysMustRevalidate(true);
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		interceptor.preHandle(request, response, null);

		assertThat(response.getHeader("Pragma"), is(""));
		assertThat(response.getHeader("Expires"), is(""));
	}
