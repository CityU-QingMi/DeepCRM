	@Test
	public void testGetBeanByTypeInstanceWithAmbiguity() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition bd1 = createConstructorDependencyBeanDefinition(99);
		RootBeanDefinition bd2 = new RootBeanDefinition(ConstructorDependency.class);
		bd2.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		bd2.getConstructorArgumentValues().addGenericArgumentValue("43");

		lbf.registerBeanDefinition("bd1", bd1);
		lbf.registerBeanDefinition("bd2", bd2);

		thrown.expect(NoUniqueBeanDefinitionException.class);
		lbf.getBean(ConstructorDependency.class, 42);
	}
