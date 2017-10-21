	@Test
	public void testAvoidCircularReferenceThroughAutowiring() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition bd = new RootBeanDefinition(ConstructorDependencyFactoryBean.class);
		bd.setAutowireMode(RootBeanDefinition.AUTOWIRE_CONSTRUCTOR);
		lbf.registerBeanDefinition("test", bd);
		RootBeanDefinition bd2 = new RootBeanDefinition(String.class);
		bd2.setAutowireMode(RootBeanDefinition.AUTOWIRE_CONSTRUCTOR);
		lbf.registerBeanDefinition("string", bd2);
		lbf.preInstantiateSingletons();
	}
