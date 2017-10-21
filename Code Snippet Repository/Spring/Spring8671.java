	@Test
	public void buildRequestSession() throws Exception {
		MockHttpServletRequest actualRequest = requestBuilder.buildRequest(servletContext);

		HttpSession newSession = actualRequest.getSession();
		assertThat(newSession, notNullValue());
		assertSingleSessionCookie(
				"JSESSIONID=" + newSession.getId() + "; Path=/test; Domain=example.com");

		webRequest.setAdditionalHeader("Cookie", "JSESSIONID=" + newSession.getId());

		requestBuilder = new HtmlUnitRequestBuilder(sessions, webClient, webRequest);
		actualRequest = requestBuilder.buildRequest(servletContext);

		assertThat(actualRequest.getSession(), sameInstance(newSession));
	}
