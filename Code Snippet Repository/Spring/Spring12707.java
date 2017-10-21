	@Test
	public void unregisterMapping() throws Exception {

		String key = "foo";
		HandlerMethod handlerMethod = new HandlerMethod(this.handler, this.method1);

		this.mapping.registerMapping(key, this.handler, this.method1);
		assertNotNull(this.mapping.getHandlerInternal(new MockHttpServletRequest("GET", key)));

		this.mapping.unregisterMapping(key);
		assertNull(mapping.getHandlerInternal(new MockHttpServletRequest("GET", key)));
		assertNull(this.mapping.getMappingRegistry().getMappingsByUrl(key));
		assertNull(this.mapping.getMappingRegistry().getHandlerMethodsByMappingName(this.method1.getName()));
		assertNull(this.mapping.getMappingRegistry().getCorsConfiguration(handlerMethod));
	}
