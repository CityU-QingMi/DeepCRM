	@Test
	public void handleInfoGetWithOrigin() throws Exception {
		this.servletRequest.setServerName("mydomain2.com");
		this.servletRequest.addHeader(HttpHeaders.ORIGIN, "http://mydomain2.com");
		resetResponseAndHandleRequest("GET", "/echo/info", HttpStatus.OK);

		assertEquals("application/json;charset=UTF-8", this.servletResponse.getContentType());
		assertEquals("no-store, no-cache, must-revalidate, max-age=0", this.servletResponse.getHeader(HttpHeaders.CACHE_CONTROL));
		String body = this.servletResponse.getContentAsString();
		assertEquals("{\"entropy\"", body.substring(0, body.indexOf(':')));
		assertEquals(",\"origins\":[\"*:*\"],\"cookie_needed\":true,\"websocket\":true}", body.substring(body.indexOf(',')));

		this.service.setAllowedOrigins(Arrays.asList("http://mydomain1.com"));
		resetResponseAndHandleRequest("GET", "/echo/info", HttpStatus.OK);

		this.service.setAllowedOrigins(Arrays.asList("http://mydomain1.com", "http://mydomain2.com", "http://mydomain3.com"));
		resetResponseAndHandleRequest("GET", "/echo/info", HttpStatus.OK);

		this.service.setAllowedOrigins(Arrays.asList("*"));
		resetResponseAndHandleRequest("GET", "/echo/info", HttpStatus.OK);

		this.servletRequest.setServerName("mydomain3.com");
		this.service.setAllowedOrigins(Arrays.asList("http://mydomain1.com"));
		resetResponseAndHandleRequest("GET", "/echo/info", HttpStatus.FORBIDDEN);
	}
