	@Test
	public void fromHttpRequestWithForwardedHostAndPort() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setScheme("http");
		request.setServerName("localhost");
		request.setServerPort(8080);
		request.addHeader("X-Forwarded-Host", "foobarhost");
		request.addHeader("X-Forwarded-Port", "9090");

		HttpRequest httpRequest = new ServletServerHttpRequest(request);
		UriComponents result = UriComponentsBuilder.fromHttpRequest(httpRequest).build();

		assertEquals("foobarhost", result.getHost());
		assertEquals(9090, result.getPort());
	}
