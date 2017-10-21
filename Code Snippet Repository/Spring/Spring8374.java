	@Test
	public void getRequestURL() {
		request.setServerPort(8080);
		request.setRequestURI("/path");
		assertEquals("http://localhost:8080/path", request.getRequestURL().toString());

		request.setScheme("https");
		request.setServerName("example.com");
		request.setServerPort(8443);
		assertEquals("https://example.com:8443/path", request.getRequestURL().toString());
	}
