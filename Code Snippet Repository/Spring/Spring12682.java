	@Test
	public void customArgumentResolvers() {
		ApplicationContext context = initContext(CustomArgumentResolverConfig.class);
		RequestMappingHandlerAdapter adapter = context.getBean(RequestMappingHandlerAdapter.class);
		HandlerExceptionResolverComposite composite = context.getBean(HandlerExceptionResolverComposite.class);

		assertNotNull(adapter);
		assertEquals(1, adapter.getCustomArgumentResolvers().size());
		assertEquals(TestArgumentResolver.class, adapter.getCustomArgumentResolvers().get(0).getClass());
		assertEquals(1, adapter.getCustomReturnValueHandlers().size());
		assertEquals(TestReturnValueHandler.class, adapter.getCustomReturnValueHandlers().get(0).getClass());

		assertNotNull(composite);
		assertEquals(3, composite.getExceptionResolvers().size());
		assertEquals(ExceptionHandlerExceptionResolver.class, composite.getExceptionResolvers().get(0).getClass());

		ExceptionHandlerExceptionResolver resolver =
				(ExceptionHandlerExceptionResolver) composite.getExceptionResolvers().get(0);

		assertEquals(1, resolver.getCustomArgumentResolvers().size());
		assertEquals(TestArgumentResolver.class, resolver.getCustomArgumentResolvers().get(0).getClass());
		assertEquals(1, resolver.getCustomReturnValueHandlers().size());
		assertEquals(TestReturnValueHandler.class, resolver.getCustomReturnValueHandlers().get(0).getClass());
	}
