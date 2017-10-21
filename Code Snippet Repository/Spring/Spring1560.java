	@Test
	public void rootBeanDefinitionAndMethodOverridesWithDifferentOverloadedValues() {
		RootBeanDefinition master = new RootBeanDefinition(TestBean.class);
		RootBeanDefinition equal = new RootBeanDefinition(TestBean.class);

		setBaseProperties(master);
		setBaseProperties(equal);

		// Simulate AbstractBeanDefinition.validate() which delegates to
		// AbstractBeanDefinition.prepareMethodOverrides():
		master.getMethodOverrides().getOverrides().iterator().next().setOverloaded(false);
		// But do not simulate validation of the 'equal' bean. As a consequence, a method
		// override in 'equal' will be marked as overloaded, but the corresponding
		// override in 'master' will not. But... the bean definitions should still be
		// considered equal.

		assertEquals("Should be equal", master, equal);
		assertEquals("Hash code for equal instances must match", master.hashCode(), equal.hashCode());
	}
