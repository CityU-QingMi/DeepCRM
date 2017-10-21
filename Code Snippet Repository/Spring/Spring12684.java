	@Test
	public void mvcViewResolverWithExistingResolver() throws Exception {
		ApplicationContext context = initContext(WebConfig.class, ViewResolverConfig.class);
		ViewResolverComposite resolver = context.getBean("mvcViewResolver", ViewResolverComposite.class);

		assertNotNull(resolver);
		assertEquals(0, resolver.getViewResolvers().size());
		assertEquals(Ordered.LOWEST_PRECEDENCE, resolver.getOrder());
		assertNull(resolver.resolveViewName("anyViewName", Locale.ENGLISH));
	}
