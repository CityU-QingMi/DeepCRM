	@Test
	public void customSupportIncludeFilterWithNonIndexedTypeUseScan() {
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.setResourceLoader(new DefaultResourceLoader(TEST_BASE_CLASSLOADER));
		// This annotation type is not directly annotated with Indexed so we can use
		// the index to find candidates
		provider.addIncludeFilter(new AnnotationTypeFilter(CustomStereotype.class));
		Set<BeanDefinition> candidates = provider.findCandidateComponents(TEST_BASE_PACKAGE);
		assertTrue(containsBeanClass(candidates, DefaultNamedComponent.class));
		assertEquals(1, candidates.size());
		assertBeanDefinitionType(candidates, ScannedGenericBeanDefinition.class);
	}
