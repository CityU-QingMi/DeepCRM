	@Test
	public void cachingConfigAndPragmaHeader() throws Exception {
		WebContentInterceptor interceptor = new WebContentInterceptor();
		interceptor.setCacheSeconds(10);
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");

		interceptor.preHandle(request, response, null);

		assertThat(response.getHeader("Pragma"), is(""));
		assertThat(response.getHeader("Expires"), is(""));
	}
