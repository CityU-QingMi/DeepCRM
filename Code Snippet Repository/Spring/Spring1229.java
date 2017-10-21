	@Test
	public void testGetBeanByTypeInstanceWithMultiplePrimary() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition bd1 = createConstructorDependencyBeanDefinition(99);
		RootBeanDefinition bd2 = createConstructorDependencyBeanDefinition(43);
		bd1.setPrimary(true);
		bd2.setPrimary(true);

		lbf.registerBeanDefinition("bd1", bd1);
		lbf.registerBeanDefinition("bd2", bd2);
		thrown.expect(NoUniqueBeanDefinitionException.class);
		thrown.expectMessage(containsString("more than one 'primary'"));
		lbf.getBean(ConstructorDependency.class, 42);
	}
