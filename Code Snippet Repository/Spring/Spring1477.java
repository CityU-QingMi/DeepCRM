	@Test
	public void testErrorOnTooManyOrTooFew() throws Exception {
		bf.registerBeanDefinition("testService", genericBeanDefinition(TestService.class).getBeanDefinition());
		bf.registerBeanDefinition("testServiceInstance2", genericBeanDefinition(TestService.class).getBeanDefinition());
		bf.registerBeanDefinition("factory",
				genericBeanDefinition(ServiceLocatorFactoryBean.class)
				.addPropertyValue("serviceLocatorInterface", TestServiceLocator.class)
				.getBeanDefinition());
		bf.registerBeanDefinition("factory2",
				genericBeanDefinition(ServiceLocatorFactoryBean.class)
				.addPropertyValue("serviceLocatorInterface", TestServiceLocator2.class)
				.getBeanDefinition());
		bf.registerBeanDefinition("factory3",
				genericBeanDefinition(ServiceLocatorFactoryBean.class)
				.addPropertyValue("serviceLocatorInterface", TestService2Locator.class)
				.getBeanDefinition());

		try {
			TestServiceLocator factory = (TestServiceLocator) bf.getBean("factory");
			factory.getTestService();
			fail("Must fail on more than one matching type");
		}
		catch (NoSuchBeanDefinitionException ex) { /* expected */ }

		try {
			TestServiceLocator2 factory = (TestServiceLocator2) bf.getBean("factory2");
			factory.getTestService(null);
			fail("Must fail on more than one matching type");
		}
		catch (NoSuchBeanDefinitionException ex) { /* expected */ }

		try {
			TestService2Locator factory = (TestService2Locator) bf.getBean("factory3");
			factory.getTestService();
			fail("Must fail on no matching types");
		}
		catch (NoSuchBeanDefinitionException ex) { /* expected */ }
	}
