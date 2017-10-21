	@Test
	public void testViewResolutionWithContentNegotiation() throws Exception {
		loadBeanDefinitions("mvc-config-view-resolution-content-negotiation.xml");

		ViewResolverComposite compositeResolver = this.appContext.getBean(ViewResolverComposite.class);
		assertNotNull(compositeResolver);
		assertEquals(1, compositeResolver.getViewResolvers().size());
		assertEquals(Ordered.HIGHEST_PRECEDENCE, compositeResolver.getOrder());

		List<ViewResolver> resolvers = compositeResolver.getViewResolvers();
		assertEquals(ContentNegotiatingViewResolver.class, resolvers.get(0).getClass());
		ContentNegotiatingViewResolver cnvr = (ContentNegotiatingViewResolver) resolvers.get(0);
		assertEquals(6, cnvr.getViewResolvers().size());
		assertEquals(1, cnvr.getDefaultViews().size());
		assertTrue(cnvr.isUseNotAcceptableStatusCode());

		String beanName = "contentNegotiationManager";
		DirectFieldAccessor accessor = new DirectFieldAccessor(cnvr);
		ContentNegotiationManager manager = (ContentNegotiationManager) accessor.getPropertyValue(beanName);
		assertNotNull(manager);
		assertSame(manager, this.appContext.getBean(ContentNegotiationManager.class));
		assertSame(manager, this.appContext.getBean("mvcContentNegotiationManager"));
	}
