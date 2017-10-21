	@Test
	public void fromHttpRequestWithForwardedPortMultiValueHeader() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setScheme("http");
		request.setServerName("localhost");
		request.setServerPort(9090);
		request.setRequestURI("/mvc-showcase");
		request.addHeader("X-Forwarded-Host", "a.example.org");
		request.addHeader("X-Forwarded-Port", "80,52022");

		HttpRequest httpRequest = new ServletServerHttpRequest(request);
		UriComponents result = UriComponentsBuilder.fromHttpRequest(httpRequest).build();

		assertEquals("http://a.example.org/mvc-showcase", result.toString());
	}
