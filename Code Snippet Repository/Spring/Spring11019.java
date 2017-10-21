	@Test
	public void fromHttpRequestForwardedHeaderWithHostPortAndWithoutServerPort() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Forwarded", "proto=https; host=84.198.58.199:9090");
		request.setScheme("http");
		request.setServerName("example.com");
		request.setRequestURI("/rest/mobile/users/1");

		HttpRequest httpRequest = new ServletServerHttpRequest(request);
		UriComponents result = UriComponentsBuilder.fromHttpRequest(httpRequest).build();

		assertEquals("https", result.getScheme());
		assertEquals("84.198.58.199", result.getHost());
		assertEquals("/rest/mobile/users/1", result.getPath());
		assertEquals(9090, result.getPort());
		assertEquals("https://84.198.58.199:9090/rest/mobile/users/1", result.toUriString());
	}
