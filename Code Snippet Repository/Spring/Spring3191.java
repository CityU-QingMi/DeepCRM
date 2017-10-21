	@Test
	public void testSimpleScanWithDefaultFiltersAndOverriddenEqualNamedBean() {
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBeanDefinition("myNamedDao", new RootBeanDefinition(NamedStubDao.class));
		int initialBeanCount = context.getBeanDefinitionCount();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
		scanner.setIncludeAnnotationConfig(false);
		int scannedBeanCount = scanner.scan(BASE_PACKAGE);

		assertEquals(5, scannedBeanCount);
		assertEquals(initialBeanCount + scannedBeanCount, context.getBeanDefinitionCount());
		assertTrue(context.containsBean("serviceInvocationCounter"));
		assertTrue(context.containsBean("fooServiceImpl"));
		assertTrue(context.containsBean("stubFooDao"));
		assertTrue(context.containsBean("myNamedComponent"));
		assertTrue(context.containsBean("myNamedDao"));
	}
