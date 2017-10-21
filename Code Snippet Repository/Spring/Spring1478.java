	@Test
	public void testErrorOnTooManyOrTooFewWithCustomServiceLocatorException() {
		bf.registerBeanDefinition("testService", genericBeanDefinition(TestService.class).getBeanDefinition());
		bf.registerBeanDefinition("testServiceInstance2", genericBeanDefinition(TestService.class).getBeanDefinition());
		bf.registerBeanDefinition("factory",
				genericBeanDefinition(ServiceLocatorFactoryBean.class)
				.addPropertyValue("serviceLocatorInterface", TestServiceLocator.class)
				.addPropertyValue("serviceLocatorExceptionClass", CustomServiceLocatorException1.class)
				.getBeanDefinition());
		bf.registerBeanDefinition("factory2",
				genericBeanDefinition(ServiceLocatorFactoryBean.class)
				.addPropertyValue("serviceLocatorInterface", TestServiceLocator2.class)
				.addPropertyValue("serviceLocatorExceptionClass", CustomServiceLocatorException2.class)
				.getBeanDefinition());
		bf.registerBeanDefinition("factory3",
				genericBeanDefinition(ServiceLocatorFactoryBean.class)
				.addPropertyValue("serviceLocatorInterface", TestService2Locator.class)
				.addPropertyValue("serviceLocatorExceptionClass", CustomServiceLocatorException3.class)
				.getBeanDefinition());

		try {
			TestServiceLocator factory = (TestServiceLocator) bf.getBean("factory");
			factory.getTestService();
			fail("Must fail on more than one matching type");
		}
		catch (CustomServiceLocatorException1 expected) {
			assertTrue(expected.getCause() instanceof NoSuchBeanDefinitionException);
		}

		try {
			TestServiceLocator2 factory2 = (TestServiceLocator2) bf.getBean("factory2");
			factory2.getTestService(null);
			fail("Must fail on more than one matching type");
		}
		catch (CustomServiceLocatorException2 expected) {
			assertTrue(expected.getCause() instanceof NoSuchBeanDefinitionException);
		}

		try {
			TestService2Locator factory3 = (TestService2Locator) bf.getBean("factory3");
			factory3.getTestService();
			fail("Must fail on no matching type");
		}
		catch (CustomServiceLocatorException3 ex) { /* expected */ }
	}
