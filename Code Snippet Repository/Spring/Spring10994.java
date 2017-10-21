	@Test
	public void fromHttpRequestWithForwardedIPv6HostAndPort() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setScheme("http");
		request.setServerName("localhost");
		request.setServerPort(-1);
		request.setRequestURI("/mvc-showcase");
		request.addHeader("X-Forwarded-Host", "[1abc:2abc:3abc::5ABC:6abc]:8080");

		HttpRequest httpRequest = new ServletServerHttpRequest(request);
		UriComponents result = UriComponentsBuilder.fromHttpRequest(httpRequest).build();

		assertEquals("http://[1abc:2abc:3abc::5ABC:6abc]:8080/mvc-showcase", result.toString());
	}
