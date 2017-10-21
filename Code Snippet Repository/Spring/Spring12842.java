	@Test
	public void customOriginDefinedViaPlaceholder() throws Exception {
		this.handlerMapping.registerHandler(new MethodLevelController());
		this.request.setRequestURI("/someOrigin");
		HandlerExecutionChain chain = this.handlerMapping.getHandler(request);
		CorsConfiguration config = getCorsConfiguration(chain, false);
		assertNotNull(config);
		assertEquals(Arrays.asList("http://example.com"), config.getAllowedOrigins());
		assertTrue(config.getAllowCredentials());
	}
