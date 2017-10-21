	@Test
	public void buildRequestCookiesMulti() {
		webRequest.setAdditionalHeader("Cookie", "name=value; name2=value2");

		MockHttpServletRequest actualRequest = requestBuilder.buildRequest(servletContext);

		Cookie[] cookies = actualRequest.getCookies();
		assertThat(cookies.length, equalTo(2));
		Cookie cookie = cookies[0];
		assertThat(cookie.getName(), equalTo("name"));
		assertThat(cookie.getValue(), equalTo("value"));
		cookie = cookies[1];
		assertThat(cookie.getName(), equalTo("name2"));
		assertThat(cookie.getValue(), equalTo("value2"));
	}
