	@Test
	public void testMultipleCustomExcludeFiltersAndDefaults() {
		GenericApplicationContext context = new GenericApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context, true);
		scanner.addExcludeFilter(new AssignableTypeFilter(FooService.class));
		scanner.addExcludeFilter(new AnnotationTypeFilter(Aspect.class));
		int beanCount = scanner.scan(BASE_PACKAGE);

		assertEquals(10, beanCount);
		assertFalse(context.containsBean("fooServiceImpl"));
		assertFalse(context.containsBean("serviceInvocationCounter"));
		assertTrue(context.containsBean("stubFooDao"));
		assertTrue(context.containsBean("myNamedComponent"));
		assertTrue(context.containsBean("myNamedDao"));
		assertTrue(context.containsBean(AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME));
		assertTrue(context.containsBean(AnnotationConfigUtils.REQUIRED_ANNOTATION_PROCESSOR_BEAN_NAME));
		assertTrue(context.containsBean(AnnotationConfigUtils.COMMON_ANNOTATION_PROCESSOR_BEAN_NAME));
		assertTrue(context.containsBean(AnnotationConfigUtils.EVENT_LISTENER_PROCESSOR_BEAN_NAME));
		assertTrue(context.containsBean(AnnotationConfigUtils.EVENT_LISTENER_FACTORY_BEAN_NAME));
	}
