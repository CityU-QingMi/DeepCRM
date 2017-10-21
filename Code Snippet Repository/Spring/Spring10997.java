	@Test
	public void fromHttpRequestWithForwardedHostMultiValuedHeader() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setScheme("http");
		request.setServerName("localhost");
		request.setServerPort(-1);
		request.addHeader("X-Forwarded-Host", "a.example.org, b.example.org, c.example.org");

		HttpRequest httpRequest = new ServletServerHttpRequest(request);
		UriComponents result = UriComponentsBuilder.fromHttpRequest(httpRequest).build();

		assertEquals("a.example.org", result.getHost());
		assertEquals(-1, result.getPort());
	}
