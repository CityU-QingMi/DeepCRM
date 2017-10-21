	@Test
	public void viewResolutionResultHandler() throws Exception {
		ApplicationContext context = loadConfig(CustomViewResolverConfig.class);

		String name = "viewResolutionResultHandler";
		ViewResolutionResultHandler handler = context.getBean(name, ViewResolutionResultHandler.class);
		assertNotNull(handler);

		assertEquals(Ordered.LOWEST_PRECEDENCE, handler.getOrder());

		List<ViewResolver> resolvers = handler.getViewResolvers();
		assertEquals(1, resolvers.size());
		assertEquals(FreeMarkerViewResolver.class, resolvers.get(0).getClass());

		List<View> views = handler.getDefaultViews();
		assertEquals(1, views.size());

		MimeType type = MimeTypeUtils.parseMimeType("application/json;charset=UTF-8");
		assertEquals(type, views.get(0).getSupportedMediaTypes().get(0));
	}
