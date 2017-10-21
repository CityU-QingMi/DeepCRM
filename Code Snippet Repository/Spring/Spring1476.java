	@Test
	public void testNoArgGetter() {
		bf.registerBeanDefinition("testService", genericBeanDefinition(TestService.class).getBeanDefinition());
		bf.registerBeanDefinition("factory",
				genericBeanDefinition(ServiceLocatorFactoryBean.class)
				.addPropertyValue("serviceLocatorInterface", TestServiceLocator.class)
				.getBeanDefinition());

		TestServiceLocator factory = (TestServiceLocator) bf.getBean("factory");
		TestService testService = factory.getTestService();
		assertNotNull(testService);
	}
