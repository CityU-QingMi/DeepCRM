	private void testCustomAssignableTypeIncludeFilter(ClassPathScanningCandidateComponentProvider provider,
			Class<? extends BeanDefinition> expectedBeanDefinitionType) {
		provider.addIncludeFilter(new AssignableTypeFilter(FooService.class));
		Set<BeanDefinition> candidates = provider.findCandidateComponents(TEST_BASE_PACKAGE);
		// Interfaces/Abstract class are filtered out automatically.
		assertTrue(containsBeanClass(candidates, AutowiredQualifierFooService.class));
		assertTrue(containsBeanClass(candidates, FooServiceImpl.class));
		assertTrue(containsBeanClass(candidates, ScopedProxyTestBean.class));
		assertEquals(3, candidates.size());
		assertBeanDefinitionType(candidates, expectedBeanDefinitionType);
	}
