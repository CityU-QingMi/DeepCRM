	@Test
	public void testGetBeanByTypeInstanceWithPrimary() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition bd1 = createConstructorDependencyBeanDefinition(99);
		RootBeanDefinition bd2 = createConstructorDependencyBeanDefinition(43);
		bd2.setPrimary(true);
		lbf.registerBeanDefinition("bd1", bd1);
		lbf.registerBeanDefinition("bd2", bd2);
		ConstructorDependency bean = lbf.getBean(ConstructorDependency.class, 42);
		assertThat(bean.beanName, equalTo("bd2"));
		assertThat(bean.spouseAge, equalTo(42));
	}
