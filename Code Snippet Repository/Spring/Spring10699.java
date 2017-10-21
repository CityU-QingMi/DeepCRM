	@Test
	public void actualRequestCaseInsensitiveOriginMatch() throws Exception {
		this.request.setMethod(HttpMethod.GET.name());
		this.request.addHeader(HttpHeaders.ORIGIN, "http://domain2.com");
		this.conf.addAllowedOrigin("http://DOMAIN2.com");

		this.processor.processRequest(this.conf, this.request, this.response);
		assertTrue(this.response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
		assertEquals(HttpServletResponse.SC_OK, this.response.getStatus());
	}
