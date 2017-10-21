	@Test
	public void mvcViewResolver() {
		ApplicationContext context = initContext(WebConfig.class);
		ViewResolverComposite resolver = context.getBean("mvcViewResolver", ViewResolverComposite.class);

		assertNotNull(resolver);
		assertEquals(1, resolver.getViewResolvers().size());
		assertEquals(InternalResourceViewResolver.class, resolver.getViewResolvers().get(0).getClass());
		assertEquals(Ordered.LOWEST_PRECEDENCE, resolver.getOrder());
	}
