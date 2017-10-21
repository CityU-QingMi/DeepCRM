	@Test
	public void scopedProxyTargetMarkedAsNonAutowireCandidate() {
		AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
		bpp.setBeanFactory(beanFactory);
		beanFactory.addBeanPostProcessor(bpp);
		beanFactory.registerBeanDefinition("config", new RootBeanDefinition(ScopedProxyConfigurationClass.class));
		beanFactory.registerBeanDefinition("consumer", new RootBeanDefinition(ScopedProxyConsumer.class));
		ConfigurationClassPostProcessor pp = new ConfigurationClassPostProcessor();
		pp.postProcessBeanFactory(beanFactory);

		ITestBean injected = beanFactory.getBean("consumer", ScopedProxyConsumer.class).testBean;
		assertTrue(injected instanceof ScopedObject);
		assertSame(beanFactory.getBean("scopedClass"), injected);
		assertSame(beanFactory.getBean(ITestBean.class), injected);
	}
