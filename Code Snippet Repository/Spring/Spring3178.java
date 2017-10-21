	@Test
	public void testCustomAssignableTypeExcludeFilterAndDefaultsWithoutPostProcessors() {
		GenericApplicationContext context = new GenericApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context, true);
		scanner.setIncludeAnnotationConfig(false);
		scanner.addExcludeFilter(new AssignableTypeFilter(FooService.class));
		int beanCount = scanner.scan(BASE_PACKAGE);

		assertEquals(5, beanCount);
		assertFalse(context.containsBean("fooServiceImpl"));
		assertTrue(context.containsBean("serviceInvocationCounter"));
		assertTrue(context.containsBean("stubFooDao"));
		assertTrue(context.containsBean("myNamedComponent"));
		assertTrue(context.containsBean("myNamedDao"));
		assertFalse(context.containsBean(AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME));
		assertFalse(context.containsBean(AnnotationConfigUtils.REQUIRED_ANNOTATION_PROCESSOR_BEAN_NAME));
		assertFalse(context.containsBean(AnnotationConfigUtils.COMMON_ANNOTATION_PROCESSOR_BEAN_NAME));
	}
