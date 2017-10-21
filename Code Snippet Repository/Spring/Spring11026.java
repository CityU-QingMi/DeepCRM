	@Test
	public void fromHttpRequest() throws URISyntaxException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setScheme("http");
		request.setServerName("localhost");
		request.setServerPort(-1);
		request.setRequestURI("/path");
		request.setQueryString("a=1");

		UriComponents result = UriComponentsBuilder.fromHttpRequest(new ServletServerHttpRequest(request)).build();
		assertEquals("http", result.getScheme());
		assertEquals("localhost", result.getHost());
		assertEquals(-1, result.getPort());
		assertEquals("/path", result.getPath());
		assertEquals("a=1", result.getQuery());
	}
