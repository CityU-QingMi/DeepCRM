	private void testDefault(ClassPathScanningCandidateComponentProvider provider,
			Class<? extends BeanDefinition> expectedBeanDefinitionType) {
		Set<BeanDefinition> candidates = provider.findCandidateComponents(TEST_BASE_PACKAGE);
		assertTrue(containsBeanClass(candidates, DefaultNamedComponent.class));
		assertTrue(containsBeanClass(candidates, NamedComponent.class));
		assertTrue(containsBeanClass(candidates, FooServiceImpl.class));
		assertTrue(containsBeanClass(candidates, StubFooDao.class));
		assertTrue(containsBeanClass(candidates, NamedStubDao.class));
		assertTrue(containsBeanClass(candidates, ServiceInvocationCounter.class));
		assertEquals(6, candidates.size());
		assertBeanDefinitionType(candidates, expectedBeanDefinitionType);
	}
