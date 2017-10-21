	@Test
	public void actualRequestWithOriginHeaderAndAllowedOrigin() throws Exception {
		this.request.setMethod(HttpMethod.GET.name());
		this.request.addHeader(HttpHeaders.ORIGIN, "http://domain2.com");
		this.conf.addAllowedOrigin("*");

		this.processor.processRequest(this.conf, this.request, this.response);
		assertTrue(this.response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
		assertEquals("*", this.response.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
		assertFalse(this.response.containsHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE));
		assertFalse(this.response.containsHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS));
		assertEquals(HttpServletResponse.SC_OK, this.response.getStatus());
	}
