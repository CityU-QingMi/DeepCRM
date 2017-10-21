	@Test
	public void testGetBeanByTypeInstanceDefinedInParent() {
		DefaultListableBeanFactory parent = new DefaultListableBeanFactory();
		RootBeanDefinition bd1 = createConstructorDependencyBeanDefinition(99);
		parent.registerBeanDefinition("bd1", bd1);
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory(parent);
		ConstructorDependency bean = lbf.getBean(ConstructorDependency.class, 42);
		assertThat(bean.beanName, equalTo("bd1"));
		assertThat(bean.spouseAge, equalTo(42));
	}
