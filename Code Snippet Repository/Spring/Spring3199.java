	private void testCustomSupportedIncludeAndExcludeFilter(ClassPathScanningCandidateComponentProvider provider,
			Class<? extends BeanDefinition> expectedBeanDefinitionType) {
		provider.addIncludeFilter(new AnnotationTypeFilter(Component.class));
		provider.addExcludeFilter(new AnnotationTypeFilter(Service.class));
		provider.addExcludeFilter(new AnnotationTypeFilter(Repository.class));
		Set<BeanDefinition> candidates = provider.findCandidateComponents(TEST_BASE_PACKAGE);
		assertTrue(containsBeanClass(candidates, NamedComponent.class));
		assertTrue(containsBeanClass(candidates, ServiceInvocationCounter.class));
		assertEquals(2, candidates.size());
		assertBeanDefinitionType(candidates, expectedBeanDefinitionType);
	}
