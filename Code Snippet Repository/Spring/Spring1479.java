	@Test
	public void testStringArgGetter() throws Exception {
		bf.registerBeanDefinition("testService", genericBeanDefinition(TestService.class).getBeanDefinition());
		bf.registerBeanDefinition("factory",
				genericBeanDefinition(ServiceLocatorFactoryBean.class)
				.addPropertyValue("serviceLocatorInterface", TestServiceLocator2.class)
				.getBeanDefinition());

		// test string-arg getter with null id
		TestServiceLocator2 factory = (TestServiceLocator2) bf.getBean("factory");

		@SuppressWarnings("unused")
		TestService testBean = factory.getTestService(null);
		// now test with explicit id
		testBean = factory.getTestService("testService");
		// now verify failure on bad id
		try {
			factory.getTestService("bogusTestService");
			fail("Illegal operation allowed");
		}
		catch (NoSuchBeanDefinitionException ex) { /* expected */ }
	}
