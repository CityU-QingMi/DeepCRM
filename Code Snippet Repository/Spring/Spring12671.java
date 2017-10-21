	@SuppressWarnings("")
	@Test
	public void viewResolvers() throws Exception {
		ViewResolverComposite viewResolver = (ViewResolverComposite) this.config.mvcViewResolver();
		assertEquals(Ordered.HIGHEST_PRECEDENCE, viewResolver.getOrder());
		List<ViewResolver> viewResolvers = viewResolver.getViewResolvers();

		DirectFieldAccessor accessor = new DirectFieldAccessor(viewResolvers.get(0));
		assertEquals(1, viewResolvers.size());
		assertEquals(ContentNegotiatingViewResolver.class, viewResolvers.get(0).getClass());
		assertFalse((Boolean) accessor.getPropertyValue("useNotAcceptableStatusCode"));
		assertNotNull(accessor.getPropertyValue("contentNegotiationManager"));

		List<View> defaultViews = (List<View>)accessor.getPropertyValue("defaultViews");
		assertNotNull(defaultViews);
		assertEquals(1, defaultViews.size());
		assertEquals(MappingJackson2JsonView.class, defaultViews.get(0).getClass());

		viewResolvers = (List<ViewResolver>)accessor.getPropertyValue("viewResolvers");
		assertNotNull(viewResolvers);
		assertEquals(1, viewResolvers.size());
		assertEquals(InternalResourceViewResolver.class, viewResolvers.get(0).getClass());
		accessor = new DirectFieldAccessor(viewResolvers.get(0));
		assertEquals("/", accessor.getPropertyValue("prefix"));
		assertEquals(".jsp", accessor.getPropertyValue("suffix"));
	}
