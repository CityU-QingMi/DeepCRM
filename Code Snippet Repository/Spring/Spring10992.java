	@Test
	public void fromHttpRequestWithForwardedIPv6() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setScheme("http");
		request.setServerName("localhost");
		request.setServerPort(-1);
		request.setRequestURI("/mvc-showcase");
		request.addHeader("Forwarded", "host=[1abc:2abc:3abc::5ABC:6abc]");

		HttpRequest httpRequest = new ServletServerHttpRequest(request);
		UriComponents result = UriComponentsBuilder.fromHttpRequest(httpRequest).build();

		assertEquals("http://[1abc:2abc:3abc::5ABC:6abc]/mvc-showcase", result.toString());
	}
