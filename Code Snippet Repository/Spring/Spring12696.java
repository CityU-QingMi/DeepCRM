	@Test
	public void preflightRequestWithMappedCorsConfiguration() throws Exception {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		this.handlerMapping.setCorsConfigurations(Collections.singletonMap("/foo", config));
		this.request.setMethod(RequestMethod.OPTIONS.name());
		this.request.setRequestURI("/foo");
		this.request.addHeader(HttpHeaders.ORIGIN, "http://domain2.com");
		this.request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
		HandlerExecutionChain chain = handlerMapping.getHandler(this.request);
		assertNotNull(chain);
		assertNotNull(chain.getHandler());
		assertTrue(chain.getHandler().getClass().getSimpleName().equals("PreFlightHandler"));
		config = getCorsConfiguration(chain, true);
		assertNotNull(config);
		assertArrayEquals(config.getAllowedOrigins().toArray(), new String[]{"*"});
	}
