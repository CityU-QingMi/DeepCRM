	@Test
	public void customOriginDefinedViaValueAttribute() throws Exception {
		this.handlerMapping.registerHandler(new MethodLevelController());
		this.request.setRequestURI("/customOrigin");
		HandlerExecutionChain chain = this.handlerMapping.getHandler(request);
		CorsConfiguration config = getCorsConfiguration(chain, false);
		assertNotNull(config);
		assertEquals(Arrays.asList("http://example.com"), config.getAllowedOrigins());
		assertTrue(config.getAllowCredentials());
	}
