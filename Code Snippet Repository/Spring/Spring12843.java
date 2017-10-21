	@Test
	public void classLevel() throws Exception {
		this.handlerMapping.registerHandler(new ClassLevelController());

		this.request.setRequestURI("/foo");
		HandlerExecutionChain chain = this.handlerMapping.getHandler(request);
		CorsConfiguration config = getCorsConfiguration(chain, false);
		assertNotNull(config);
		assertArrayEquals(new String[] {"GET"}, config.getAllowedMethods().toArray());
		assertArrayEquals(new String[] {"*"}, config.getAllowedOrigins().toArray());
		assertFalse(config.getAllowCredentials());

		this.request.setRequestURI("/bar");
		chain = this.handlerMapping.getHandler(request);
		config = getCorsConfiguration(chain, false);
		assertNotNull(config);
		assertArrayEquals(new String[] {"GET"}, config.getAllowedMethods().toArray());
		assertArrayEquals(new String[] {"*"}, config.getAllowedOrigins().toArray());
		assertFalse(config.getAllowCredentials());

		this.request.setRequestURI("/baz");
		chain = this.handlerMapping.getHandler(request);
		config = getCorsConfiguration(chain, false);
		assertNotNull(config);
		assertArrayEquals(new String[] {"GET"}, config.getAllowedMethods().toArray());
		assertArrayEquals(new String[] {"*"}, config.getAllowedOrigins().toArray());
		assertTrue(config.getAllowCredentials());
	}
