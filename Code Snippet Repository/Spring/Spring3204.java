	@Test
	public void testExcludeTakesPrecedence() {
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AnnotationTypeFilter(Component.class));
		provider.addIncludeFilter(new AssignableTypeFilter(FooServiceImpl.class));
		provider.addExcludeFilter(new AssignableTypeFilter(FooService.class));
		Set<BeanDefinition> candidates = provider.findCandidateComponents(TEST_BASE_PACKAGE);
		assertEquals(5, candidates.size());
		assertTrue(containsBeanClass(candidates, NamedComponent.class));
		assertTrue(containsBeanClass(candidates, ServiceInvocationCounter.class));
		assertFalse(containsBeanClass(candidates, FooServiceImpl.class));
	}
