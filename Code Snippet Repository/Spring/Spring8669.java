	@Test
	public void buildRequestAndAntPathRequestMatcher() throws Exception {
		webRequest.setUrl(new URL("http://example.com/app/login/authenticate"));

		MockHttpServletRequest actualRequest = requestBuilder.buildRequest(servletContext);

		// verify it is going to work with Spring Security's AntPathRequestMatcher
		assertThat(actualRequest.getPathInfo(), nullValue());
		assertThat(actualRequest.getServletPath(), equalTo("/login/authenticate"));
	}
