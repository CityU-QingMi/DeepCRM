	@Test
	public void testDependentRegistration() {
		DefaultSingletonBeanRegistry beanRegistry = new DefaultSingletonBeanRegistry();

		beanRegistry.registerDependentBean("a", "b");
		beanRegistry.registerDependentBean("b", "c");
		beanRegistry.registerDependentBean("c", "b");
		assertTrue(beanRegistry.isDependent("a", "b"));
		assertTrue(beanRegistry.isDependent("b", "c"));
		assertTrue(beanRegistry.isDependent("c", "b"));
		assertTrue(beanRegistry.isDependent("a", "c"));
		assertFalse(beanRegistry.isDependent("c", "a"));
		assertFalse(beanRegistry.isDependent("b", "a"));
		assertFalse(beanRegistry.isDependent("a", "a"));
		assertTrue(beanRegistry.isDependent("b", "b"));
		assertTrue(beanRegistry.isDependent("c", "c"));
	}
