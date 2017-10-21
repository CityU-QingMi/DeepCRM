	@Test
	public void emptyViewControllerHandlerMapping() {
		ApplicationContext context = initContext(WebConfig.class);
		String name = "viewControllerHandlerMapping";
		AbstractHandlerMapping handlerMapping = context.getBean(name, AbstractHandlerMapping.class);

		assertNotNull(handlerMapping);
		assertEquals(Integer.MAX_VALUE, handlerMapping.getOrder());
		assertTrue(handlerMapping.getClass().getName().endsWith("EmptyHandlerMapping"));
	}
