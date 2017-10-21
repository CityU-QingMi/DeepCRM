	@Test
	public void mergeCookie() throws Exception {
		String cookieName = "PARENT";
		String cookieValue = "VALUE";
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new HelloController())
				.defaultRequest(get("/").cookie(new Cookie(cookieName, cookieValue)))
				.build();

		Cookie[] cookies = mockMvc.perform(requestBuilder).andReturn().getRequest().getCookies();
		assertThat(cookies, notNullValue());
		assertThat(cookies.length, equalTo(1));
		Cookie cookie = cookies[0];
		assertThat(cookie.getName(), equalTo(cookieName));
		assertThat(cookie.getValue(), equalTo(cookieValue));
	}
