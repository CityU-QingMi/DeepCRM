	@Test
	public void buildRequestSessionInvalidate() throws Exception {
		String sessionId = "session-id";
		webRequest.setAdditionalHeader("Cookie", "JSESSIONID=" + sessionId);

		MockHttpServletRequest actualRequest = requestBuilder.buildRequest(servletContext);
		HttpSession sessionToRemove = actualRequest.getSession();
		sessionToRemove.invalidate();

		assertThat(sessions.containsKey(sessionToRemove.getId()), equalTo(false));
		assertSingleSessionCookie("JSESSIONID=" + sessionToRemove.getId()
				+ "; Expires=Thu, 01-Jan-1970 00:00:01 GMT; Path=/test; Domain=example.com");

		webRequest.removeAdditionalHeader("Cookie");
		requestBuilder = new HtmlUnitRequestBuilder(sessions, webClient, webRequest);

		actualRequest = requestBuilder.buildRequest(servletContext);

		assertThat(actualRequest.getSession().isNew(), equalTo(true));
		assertThat(sessions.containsKey(sessionToRemove.getId()), equalTo(false));
	}
