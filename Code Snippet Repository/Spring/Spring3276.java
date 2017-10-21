	@Test
	public void testSelfReferenceExclusionForFactoryMethodOnSameBean() {
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(beanFactory);
		beanFactory.addBeanPostProcessor(bpp);
		beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
		beanFactory.registerBeanDefinition("configClass", new RootBeanDefinition(ConcreteConfig.class));
		beanFactory.registerBeanDefinition("serviceBeanProvider", new RootBeanDefinition(ServiceBeanProvider.class));
		new ConfigurationClassPostProcessor().postProcessBeanFactory(beanFactory);
		beanFactory.preInstantiateSingletons();

		beanFactory.getBean(ServiceBean.class);
	}
