	@Test
	public void testSimpleScanWithDefaultFiltersAndPrimaryLazyBean() {
		GenericApplicationContext context = new GenericApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
		scanner.scan(BASE_PACKAGE);
		scanner.scan("org.springframework.context.annotation5");
		assertTrue(context.containsBean("serviceInvocationCounter"));
		assertTrue(context.containsBean("fooServiceImpl"));
		assertTrue(context.containsBean("stubFooDao"));
		assertTrue(context.containsBean("myNamedComponent"));
		assertTrue(context.containsBean("myNamedDao"));
		assertTrue(context.containsBean("otherFooDao"));
		context.refresh();

		assertFalse(context.getBeanFactory().containsSingleton("otherFooDao"));
		assertFalse(context.getBeanFactory().containsSingleton("fooServiceImpl"));
		FooServiceImpl fooService = context.getBean("fooServiceImpl", FooServiceImpl.class);
		assertTrue(context.getBeanFactory().containsSingleton("otherFooDao"));
		assertEquals("other", fooService.foo(123));
		assertEquals("other", fooService.lookupFoo(123));
	}
