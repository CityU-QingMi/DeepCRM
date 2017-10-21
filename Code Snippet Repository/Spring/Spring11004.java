	@Test
	public void fromHttpRequestWithForwardedProtoMultiValueHeader() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setScheme("http");
		request.setServerName("localhost");
		request.setServerPort(8080);
		request.setRequestURI("/mvc-showcase");
		request.addHeader("X-Forwarded-Host", "a.example.org");
		request.addHeader("X-Forwarded-Port", "443");
		request.addHeader("X-Forwarded-Proto", "https,https");

		HttpRequest httpRequest = new ServletServerHttpRequest(request);
		UriComponents result = UriComponentsBuilder.fromHttpRequest(httpRequest).build();

		assertEquals("https://a.example.org/mvc-showcase", result.toString());
	}
