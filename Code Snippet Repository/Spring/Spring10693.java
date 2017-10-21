	@Test
	public void preflightRequestAllowsAllHeaders() throws Exception {
		this.request.setMethod(HttpMethod.OPTIONS.name());
		this.request.addHeader(HttpHeaders.ORIGIN, "http://domain2.com");
		this.request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
		this.request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, "Header1, Header2");
		this.conf.addAllowedHeader("*");
		this.conf.addAllowedOrigin("http://domain2.com");

		this.processor.processRequest(this.conf, this.request, this.response);
		assertTrue(this.response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
		assertTrue(this.response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS));
		assertTrue(this.response.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS).contains("Header1"));
		assertTrue(this.response.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS).contains("Header2"));
		assertFalse(this.response.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS).contains("*"));
		assertEquals(HttpServletResponse.SC_OK, this.response.getStatus());
	}
