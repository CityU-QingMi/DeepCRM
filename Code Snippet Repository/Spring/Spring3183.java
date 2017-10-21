	@Test
	public void testBeanAutowiredWithAnnotationConfigEnabled() {
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBeanDefinition("myBf", new RootBeanDefinition(StaticListableBeanFactory.class));
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
		scanner.setBeanNameGenerator(new TestBeanNameGenerator());
		int beanCount = scanner.scan(BASE_PACKAGE);
		assertEquals(12, beanCount);
		context.refresh();

		FooServiceImpl fooService = context.getBean("fooService", FooServiceImpl.class);
		StaticListableBeanFactory myBf = (StaticListableBeanFactory) context.getBean("myBf");
		MessageSource ms = (MessageSource) context.getBean("messageSource");
		assertTrue(fooService.isInitCalled());
		assertEquals("bar", fooService.foo(123));
		assertEquals("bar", fooService.lookupFoo(123));
		assertSame(context.getDefaultListableBeanFactory(), fooService.beanFactory);
		assertEquals(2, fooService.listableBeanFactory.size());
		assertSame(context.getDefaultListableBeanFactory(), fooService.listableBeanFactory.get(0));
		assertSame(myBf, fooService.listableBeanFactory.get(1));
		assertSame(context, fooService.resourceLoader);
		assertSame(context, fooService.resourcePatternResolver);
		assertSame(context, fooService.eventPublisher);
		assertSame(ms, fooService.messageSource);
		assertSame(context, fooService.context);
		assertEquals(1, fooService.configurableContext.length);
		assertSame(context, fooService.configurableContext[0]);
		assertSame(context, fooService.genericContext);
	}
