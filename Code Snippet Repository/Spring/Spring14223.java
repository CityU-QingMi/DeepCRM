	@Test
	public void handleInfoGet() throws Exception {
		resetResponseAndHandleRequest("GET", "/echo/info", HttpStatus.OK);

		assertEquals("application/json;charset=UTF-8", this.servletResponse.getContentType());
		assertEquals("no-store, no-cache, must-revalidate, max-age=0", this.servletResponse.getHeader(HttpHeaders.CACHE_CONTROL));
		assertNull(this.servletResponse.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
		assertNull(this.servletResponse.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS));
		assertNull(this.servletResponse.getHeader(HttpHeaders.VARY));

		String body = this.servletResponse.getContentAsString();
		assertEquals("{\"entropy\"", body.substring(0, body.indexOf(':')));
		assertEquals(",\"origins\":[\"*:*\"],\"cookie_needed\":true,\"websocket\":true}",
				body.substring(body.indexOf(',')));

		this.service.setSessionCookieNeeded(false);
		this.service.setWebSocketEnabled(false);
		resetResponseAndHandleRequest("GET", "/echo/info", HttpStatus.OK);

		body = this.servletResponse.getContentAsString();
		assertEquals(",\"origins\":[\"*:*\"],\"cookie_needed\":false,\"websocket\":false}",
				body.substring(body.indexOf(',')));

		this.service.setAllowedOrigins(Arrays.asList("http://mydomain1.com"));
		resetResponseAndHandleRequest("GET", "/echo/info", HttpStatus.OK);
		assertNull(this.servletResponse.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
		assertNull(this.servletResponse.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS));
		assertNull(this.servletResponse.getHeader(HttpHeaders.VARY));
	}
