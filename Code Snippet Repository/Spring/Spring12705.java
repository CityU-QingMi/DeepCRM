	@Test
	public void registerMapping() throws Exception {

		String key1 = "/foo";
		String key2 = "/foo*";
		this.mapping.registerMapping(key1, this.handler, this.method1);
		this.mapping.registerMapping(key2, this.handler, this.method2);

		// Direct URL lookup

		List directUrlMatches = this.mapping.getMappingRegistry().getMappingsByUrl(key1);
		assertNotNull(directUrlMatches);
		assertEquals(1, directUrlMatches.size());
		assertEquals(key1, directUrlMatches.get(0));

		// Mapping name lookup

		HandlerMethod handlerMethod1 = new HandlerMethod(this.handler, this.method1);
		HandlerMethod handlerMethod2 = new HandlerMethod(this.handler, this.method2);

		String name1 = this.method1.getName();
		List<HandlerMethod> handlerMethods = this.mapping.getMappingRegistry().getHandlerMethodsByMappingName(name1);
		assertNotNull(handlerMethods);
		assertEquals(1, handlerMethods.size());
		assertEquals(handlerMethod1, handlerMethods.get(0));

		String name2 = this.method2.getName();
		handlerMethods = this.mapping.getMappingRegistry().getHandlerMethodsByMappingName(name2);
		assertNotNull(handlerMethods);
		assertEquals(1, handlerMethods.size());
		assertEquals(handlerMethod2, handlerMethods.get(0));

		// CORS lookup

		CorsConfiguration config = this.mapping.getMappingRegistry().getCorsConfiguration(handlerMethod1);
		assertNotNull(config);
		assertEquals("http://" + handler.hashCode() + name1, config.getAllowedOrigins().get(0));

		config = this.mapping.getMappingRegistry().getCorsConfiguration(handlerMethod2);
		assertNotNull(config);
		assertEquals("http://" + handler.hashCode() + name2, config.getAllowedOrigins().get(0));
	}
