	@Test
	public void fromHttpRequestWithForwardedHostIncludingPort() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setScheme("http");
		request.setServerName("localhost");
		request.setServerPort(-1);
		request.setRequestURI("/mvc-showcase");
		request.addHeader("X-Forwarded-Host", "webtest.foo.bar.com:443");

		HttpRequest httpRequest = new ServletServerHttpRequest(request);
		UriComponents result = UriComponentsBuilder.fromHttpRequest(httpRequest).build();

		assertEquals("webtest.foo.bar.com", result.getHost());
		assertEquals(443, result.getPort());
	}
