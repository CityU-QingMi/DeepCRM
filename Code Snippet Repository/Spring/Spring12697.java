	@Test
	public void detectHandlerMappings() throws Exception {
		StaticWebApplicationContext cxt = new StaticWebApplicationContext();
		cxt.registerSingleton("hmA", SimpleUrlHandlerMapping.class);
		cxt.registerSingleton("hmB", SimpleUrlHandlerMapping.class);
		cxt.registerSingleton("hmC", SimpleUrlHandlerMapping.class);
		cxt.refresh();

		List<?> expected = Arrays.asList(cxt.getBean("hmA"), cxt.getBean("hmB"), cxt.getBean("hmC"));
		List<HandlerMapping> actual = getIntrospector(cxt).getHandlerMappings();

		assertEquals(expected, actual);
	}
