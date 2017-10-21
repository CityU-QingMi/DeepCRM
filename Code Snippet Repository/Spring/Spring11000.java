	@Test
	public void fromHttpRequestWithForwardedHostWithForwardedScheme() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setScheme("http");
		request.setServerName("localhost");
		request.setServerPort(10080);
		request.addHeader("X-Forwarded-Host", "example.org");
		request.addHeader("X-Forwarded-Proto", "https");

		HttpRequest httpRequest = new ServletServerHttpRequest(request);
		UriComponents result = UriComponentsBuilder.fromHttpRequest(httpRequest).build();

		assertEquals("example.org", result.getHost());
		assertEquals("https", result.getScheme());
		assertEquals(-1, result.getPort());
	}
