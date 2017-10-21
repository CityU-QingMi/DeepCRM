	@Test
	public void testConfigWithDefaultMethodsUsingAsm() {
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(beanFactory);
		beanFactory.addBeanPostProcessor(bpp);
		beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
		beanFactory.registerBeanDefinition("configClass", new RootBeanDefinition(ConcreteConfigWithDefaultMethods.class.getName()));
		beanFactory.registerBeanDefinition("serviceBeanProvider", new RootBeanDefinition(ServiceBeanProvider.class.getName()));
		new ConfigurationClassPostProcessor().postProcessBeanFactory(beanFactory);
		beanFactory.preInstantiateSingletons();

		beanFactory.getBean(ServiceBean.class);
	}
