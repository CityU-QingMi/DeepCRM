	@Test
	public void testWithFactoryBean() {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		RootBeanDefinition beanDef = new RootBeanDefinition(RequiredTestBean.class);
		beanDef.setFactoryBeanName("testBeanFactory");
		beanDef.setFactoryMethodName("create");
		factory.registerBeanDefinition("testBean", beanDef);
		factory.registerBeanDefinition("testBeanFactory", new RootBeanDefinition(RequiredTestBeanFactory.class));
		RequiredAnnotationBeanPostProcessor bpp = new RequiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(factory);
		factory.addBeanPostProcessor(bpp);
		factory.preInstantiateSingletons();
	}
