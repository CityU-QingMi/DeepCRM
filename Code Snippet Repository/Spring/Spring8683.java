	@Test
	public void buildRequestCookiesSingle() {
		webRequest.setAdditionalHeader("Cookie", "name=value");

		MockHttpServletRequest actualRequest = requestBuilder.buildRequest(servletContext);

		Cookie[] cookies = actualRequest.getCookies();
		assertThat(cookies.length, equalTo(1));
		assertThat(cookies[0].getName(), equalTo("name"));
		assertThat(cookies[0].getValue(), equalTo("value"));
	}
