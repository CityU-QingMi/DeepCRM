	@Test
	public void testGetBeanWithArgsNotCreatedForFactoryBeanChecking() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition bd1 = new RootBeanDefinition(ConstructorDependency.class);
		bd1.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		lbf.registerBeanDefinition("bd1", bd1);
		RootBeanDefinition bd2 = new RootBeanDefinition(ConstructorDependencyFactoryBean.class);
		bd2.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		lbf.registerBeanDefinition("bd2", bd2);

		ConstructorDependency bean = lbf.getBean(ConstructorDependency.class, 42);
		assertThat(bean.beanName, equalTo("bd1"));
		assertThat(bean.spouseAge, equalTo(42));

		assertEquals(1, lbf.getBeanNamesForType(ConstructorDependency.class).length);
		assertEquals(1, lbf.getBeanNamesForType(ConstructorDependencyFactoryBean.class).length);
		assertEquals(1, lbf.getBeanNamesForType(ResolvableType.forClassWithGenerics(FactoryBean.class, Object.class)).length);
		assertEquals(0, lbf.getBeanNamesForType(ResolvableType.forClassWithGenerics(FactoryBean.class, String.class)).length);
	}
