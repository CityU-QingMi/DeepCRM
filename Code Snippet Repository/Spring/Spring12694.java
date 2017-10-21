	@Test
	public void preflightRequestWithCorsConfigurationProvider() throws Exception {
		this.request.setMethod(RequestMethod.OPTIONS.name());
		this.request.setRequestURI("/cors");
		this.request.addHeader(HttpHeaders.ORIGIN, "http://domain2.com");
		this.request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
		HandlerExecutionChain chain = handlerMapping.getHandler(this.request);
		assertNotNull(chain);
		assertNotNull(chain.getHandler());
		assertTrue(chain.getHandler().getClass().getSimpleName().equals("PreFlightHandler"));
		CorsConfiguration config = getCorsConfiguration(chain, true);
		assertNotNull(config);
		assertArrayEquals(config.getAllowedOrigins().toArray(), new String[]{"*"});
	}
