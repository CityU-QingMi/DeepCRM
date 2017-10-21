	@Test
	public void customNotSupportedIncludeFilterUseScan() {
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.setResourceLoader(new DefaultResourceLoader(TEST_BASE_CLASSLOADER));
		provider.addIncludeFilter(new AssignableTypeFilter(FooDao.class));
		Set<BeanDefinition> candidates = provider.findCandidateComponents(TEST_BASE_PACKAGE);
		assertTrue(containsBeanClass(candidates, StubFooDao.class));
		assertEquals(1, candidates.size());
		assertBeanDefinitionType(candidates, ScannedGenericBeanDefinition.class);
	}
