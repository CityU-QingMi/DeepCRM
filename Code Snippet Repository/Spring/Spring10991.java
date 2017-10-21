	@Test
	public void fromHttpRequestWithForwardedIPv4Host() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setScheme("http");
		request.setServerName("localhost");
		request.setServerPort(-1);
		request.setRequestURI("/mvc-showcase");
		request.addHeader("Forwarded", "host=192.168.0.1");

		HttpRequest httpRequest = new ServletServerHttpRequest(request);
		UriComponents result = UriComponentsBuilder.fromHttpRequest(httpRequest).build();

		assertEquals("http://192.168.0.1/mvc-showcase", result.toString());
	}
