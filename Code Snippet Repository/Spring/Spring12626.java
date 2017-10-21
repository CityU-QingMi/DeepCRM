	@Test
	public void testPathMatchingHandlerMappings() throws Exception {
		loadBeanDefinitions("mvc-config-path-matching-mappings.xml");

		RequestMappingHandlerMapping requestMapping = appContext.getBean(RequestMappingHandlerMapping.class);
		assertNotNull(requestMapping);
		assertEquals(TestPathHelper.class, requestMapping.getUrlPathHelper().getClass());
		assertEquals(TestPathMatcher.class, requestMapping.getPathMatcher().getClass());

		SimpleUrlHandlerMapping viewController = appContext.getBean(VIEWCONTROLLER_BEAN_NAME, SimpleUrlHandlerMapping.class);
		assertNotNull(viewController);
		assertEquals(TestPathHelper.class, viewController.getUrlPathHelper().getClass());
		assertEquals(TestPathMatcher.class, viewController.getPathMatcher().getClass());

		for (SimpleUrlHandlerMapping handlerMapping : appContext.getBeansOfType(SimpleUrlHandlerMapping.class).values()) {
			assertNotNull(handlerMapping);
			assertEquals(TestPathHelper.class, handlerMapping.getUrlPathHelper().getClass());
			assertEquals(TestPathMatcher.class, handlerMapping.getPathMatcher().getClass());
		}
	}
