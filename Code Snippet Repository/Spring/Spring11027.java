	@Test
	public void fromHttpRequestResetsPortBeforeSettingIt() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("X-Forwarded-Proto", "https");
		request.addHeader("X-Forwarded-Host", "84.198.58.199");
		request.addHeader("X-Forwarded-Port", 443);
		request.setScheme("http");
		request.setServerName("example.com");
		request.setServerPort(80);
		request.setRequestURI("/rest/mobile/users/1");

		HttpRequest httpRequest = new ServletServerHttpRequest(request);
		UriComponents result = UriComponentsBuilder.fromHttpRequest(httpRequest).build();

		assertEquals("https", result.getScheme());
		assertEquals("84.198.58.199", result.getHost());
		assertEquals(-1, result.getPort());
		assertEquals("/rest/mobile/users/1", result.getPath());
	}
