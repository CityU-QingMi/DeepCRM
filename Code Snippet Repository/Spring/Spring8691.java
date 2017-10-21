	@Test
	public void cookieManagerShared() throws Exception {
		WebConnectionHtmlUnitDriver otherDriver = new WebConnectionHtmlUnitDriver();
		this.mockMvc = MockMvcBuilders.standaloneSetup(new CookieController()).build();
		this.driver = MockMvcHtmlUnitDriverBuilder.mockMvcSetup(this.mockMvc)
				.withDelegate(otherDriver).build();

		assertThat(get("http://localhost/"), equalTo(""));
		Cookie cookie = new Cookie("localhost", "cookie", "cookieManagerShared");
		otherDriver.getWebClient().getCookieManager().addCookie(cookie);
		assertThat(get("http://localhost/"), equalTo("cookieManagerShared"));
	}
