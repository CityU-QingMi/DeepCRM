	@Test
	public void resourceHandler() throws Exception {
		ApplicationContext context = loadConfig(CustomResourceHandlingConfig.class);

		String name = "resourceHandlerMapping";
		AbstractUrlHandlerMapping handlerMapping = context.getBean(name, AbstractUrlHandlerMapping.class);
		assertNotNull(handlerMapping);

		assertEquals(Ordered.LOWEST_PRECEDENCE - 1, handlerMapping.getOrder());

		SimpleUrlHandlerMapping urlHandlerMapping = (SimpleUrlHandlerMapping) handlerMapping;
		WebHandler webHandler = (WebHandler) urlHandlerMapping.getUrlMap().get("/images/**");
		assertNotNull(webHandler);
	}
