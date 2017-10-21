	@Test
	public void buildRequestSessionWithExistingSession() throws Exception {
		String sessionId = "session-id";
		webRequest.setAdditionalHeader("Cookie", "JSESSIONID=" + sessionId);
		MockHttpServletRequest actualRequest = requestBuilder.buildRequest(servletContext);

		HttpSession session = actualRequest.getSession();
		assertThat(session.getId(), equalTo(sessionId));
		assertSingleSessionCookie("JSESSIONID=" + session.getId() + "; Path=/test; Domain=example.com");

		requestBuilder = new HtmlUnitRequestBuilder(sessions, webClient, webRequest);
		actualRequest = requestBuilder.buildRequest(servletContext);
		assertThat(actualRequest.getSession(), equalTo(session));

		webRequest.setAdditionalHeader("Cookie", "JSESSIONID=" + sessionId + "NEW");
		actualRequest = requestBuilder.buildRequest(servletContext);
		assertThat(actualRequest.getSession(), not(equalTo(session)));
		assertSingleSessionCookie("JSESSIONID=" + actualRequest.getSession().getId()
				+ "; Path=/test; Domain=example.com");
	}
