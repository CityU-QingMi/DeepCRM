	@Test
	public void testResourcesWithOptionalAttributes() throws Exception {
		loadBeanDefinitions("mvc-config-resources-optional-attrs.xml");

		SimpleUrlHandlerMapping mapping = appContext.getBean(SimpleUrlHandlerMapping.class);
		assertNotNull(mapping);
		assertEquals(5, mapping.getOrder());
		assertNotNull(mapping.getUrlMap().get("/resources/**"));

		ResourceHttpRequestHandler handler = appContext.getBean((String) mapping.getUrlMap().get("/resources/**"),
				ResourceHttpRequestHandler.class);
		assertNotNull(handler);
		assertEquals(3600, handler.getCacheSeconds());
	}
