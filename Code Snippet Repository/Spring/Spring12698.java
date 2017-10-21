	@Test
	public void detectHandlerMappingsOrdered() throws Exception {
		StaticWebApplicationContext cxt = new StaticWebApplicationContext();
		MutablePropertyValues pvs = new MutablePropertyValues(Collections.singletonMap("order", "3"));
		cxt.registerSingleton("hmA", SimpleUrlHandlerMapping.class, pvs);
		pvs = new MutablePropertyValues(Collections.singletonMap("order", "2"));
		cxt.registerSingleton("hmB", SimpleUrlHandlerMapping.class, pvs);
		pvs = new MutablePropertyValues(Collections.singletonMap("order", "1"));
		cxt.registerSingleton("hmC", SimpleUrlHandlerMapping.class, pvs);
		cxt.refresh();

		List<?> expected = Arrays.asList(cxt.getBean("hmC"), cxt.getBean("hmB"), cxt.getBean("hmA"));
		List<HandlerMapping> actual = getIntrospector(cxt).getHandlerMappings();

		assertEquals(expected, actual);
	}
