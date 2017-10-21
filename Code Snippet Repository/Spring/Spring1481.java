	@Ignore @Test
	public void testServiceMappings() {
		bf.registerBeanDefinition("testService1", genericBeanDefinition(TestService.class).getBeanDefinition());
		bf.registerBeanDefinition("testService2", genericBeanDefinition(ExtendedTestService.class).getBeanDefinition());
		bf.registerBeanDefinition("factory",
				genericBeanDefinition(ServiceLocatorFactoryBean.class)
				.addPropertyValue("serviceLocatorInterface", TestServiceLocator3.class)
				.addPropertyValue("serviceMappings", "=testService1\n1=testService1\n2=testService2")
				.getBeanDefinition());

//		StaticApplicationContext ctx = new StaticApplicationContext();
//		ctx.registerPrototype("testService1", TestService.class, new MutablePropertyValues());
//		ctx.registerPrototype("testService2", ExtendedTestService.class, new MutablePropertyValues());
//		MutablePropertyValues mpv = new MutablePropertyValues();
//		mpv.addPropertyValue("serviceLocatorInterface", TestServiceLocator3.class);
//		mpv.addPropertyValue("serviceMappings", "=testService1\n1=testService1\n2=testService2");
//		ctx.registerSingleton("factory", ServiceLocatorFactoryBean.class, mpv);
//		ctx.refresh();

		TestServiceLocator3 factory = (TestServiceLocator3) bf.getBean("factory");
		TestService testBean1 = factory.getTestService();
		TestService testBean2 = factory.getTestService("testService1");
		TestService testBean3 = factory.getTestService(1);
		TestService testBean4 = factory.getTestService(2);
		assertNotSame(testBean1, testBean2);
		assertNotSame(testBean1, testBean3);
		assertNotSame(testBean1, testBean4);
		assertNotSame(testBean2, testBean3);
		assertNotSame(testBean2, testBean4);
		assertNotSame(testBean3, testBean4);
		assertFalse(testBean1 instanceof ExtendedTestService);
		assertFalse(testBean2 instanceof ExtendedTestService);
		assertFalse(testBean3 instanceof ExtendedTestService);
		assertTrue(testBean4 instanceof ExtendedTestService);
	}
