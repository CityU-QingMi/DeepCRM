	@Test
	public void mvcViewResolverWithOrderSet() {
		ApplicationContext context = initContext(CustomViewResolverOrderConfig.class);
		ViewResolverComposite resolver = context.getBean("mvcViewResolver", ViewResolverComposite.class);

		assertNotNull(resolver);
		assertEquals(1, resolver.getViewResolvers().size());
		assertEquals(InternalResourceViewResolver.class, resolver.getViewResolvers().get(0).getClass());
		assertEquals(123, resolver.getOrder());
	}
