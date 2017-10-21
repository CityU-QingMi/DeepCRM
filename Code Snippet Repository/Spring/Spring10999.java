	@Test
	public void fromHttpRequestWithForwardedHostWithDefaultPort() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setScheme("http");
		request.setServerName("localhost");
		request.setServerPort(10080);
		request.addHeader("X-Forwarded-Host", "example.org");

		HttpRequest httpRequest = new ServletServerHttpRequest(request);
		UriComponents result = UriComponentsBuilder.fromHttpRequest(httpRequest).build();

		assertEquals("example.org", result.getHost());
		assertEquals(-1, result.getPort());
	}
