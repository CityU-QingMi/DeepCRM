	@Test
	public void registerMappingWithSameMethodAndTwoHandlerInstances() throws Exception {

		String key1 = "foo";
		String key2 = "bar";

		MyHandler handler1 = new MyHandler();
		MyHandler handler2 = new MyHandler();

		HandlerMethod handlerMethod1 = new HandlerMethod(handler1, this.method1);
		HandlerMethod handlerMethod2 = new HandlerMethod(handler2, this.method1);

		this.mapping.registerMapping(key1, handler1, this.method1);
		this.mapping.registerMapping(key2, handler2, this.method1);

		// Direct URL lookup

		List directUrlMatches = this.mapping.getMappingRegistry().getMappingsByUrl(key1);
		assertNotNull(directUrlMatches);
		assertEquals(1, directUrlMatches.size());
		assertEquals(key1, directUrlMatches.get(0));

		// Mapping name lookup

		String name = this.method1.getName();
		List<HandlerMethod> handlerMethods = this.mapping.getMappingRegistry().getHandlerMethodsByMappingName(name);
		assertNotNull(handlerMethods);
		assertEquals(2, handlerMethods.size());
		assertEquals(handlerMethod1, handlerMethods.get(0));
		assertEquals(handlerMethod2, handlerMethods.get(1));

		// CORS lookup

		CorsConfiguration config = this.mapping.getMappingRegistry().getCorsConfiguration(handlerMethod1);
		assertNotNull(config);
		assertEquals("http://" + handler1.hashCode() + name, config.getAllowedOrigins().get(0));

		config = this.mapping.getMappingRegistry().getCorsConfiguration(handlerMethod2);
		assertNotNull(config);
		assertEquals("http://" + handler2.hashCode() + name, config.getAllowedOrigins().get(0));
	}
