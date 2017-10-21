	@Test @SuppressWarnings("")
	public void defaultHandlerMappings() throws Exception {
		StaticWebApplicationContext cxt = new StaticWebApplicationContext();
		cxt.refresh();

		List<HandlerMapping> actual = getIntrospector(cxt).getHandlerMappings();
		assertEquals(2, actual.size());
		assertEquals(BeanNameUrlHandlerMapping.class, actual.get(0).getClass());
		assertEquals(RequestMappingHandlerMapping.class, actual.get(1).getClass());
	}
