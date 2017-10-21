	@Test
	public void handleInfoGetCorsFilter() throws Exception {

		// Simulate scenario where Filter would have already set CORS headers
		this.servletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "foobar:123");

		handleRequest("GET", "/echo/info", HttpStatus.OK);

		assertEquals("foobar:123", this.servletResponse.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
	}
