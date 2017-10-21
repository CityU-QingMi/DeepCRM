	@Ignore @Test
	public void testCombinedLocatorInterface() {
		bf.registerBeanDefinition("testService", genericBeanDefinition(TestService.class).getBeanDefinition());
		bf.registerAlias("testService", "1");

		bf.registerBeanDefinition("factory",
				genericBeanDefinition(ServiceLocatorFactoryBean.class)
				.addPropertyValue("serviceLocatorInterface", TestServiceLocator3.class)
				.getBeanDefinition());

//		StaticApplicationContext ctx = new StaticApplicationContext();
//		ctx.registerPrototype("testService", TestService.class, new MutablePropertyValues());
//		ctx.registerAlias("testService", "1");
//		MutablePropertyValues mpv = new MutablePropertyValues();
//		mpv.addPropertyValue("serviceLocatorInterface", TestServiceLocator3.class);
//		ctx.registerSingleton("factory", ServiceLocatorFactoryBean.class, mpv);
//		ctx.refresh();

		TestServiceLocator3 factory = (TestServiceLocator3) bf.getBean("factory");
		TestService testBean1 = factory.getTestService();
		TestService testBean2 = factory.getTestService("testService");
		TestService testBean3 = factory.getTestService(1);
		TestService testBean4 = factory.someFactoryMethod();
		assertNotSame(testBean1, testBean2);
		assertNotSame(testBean1, testBean3);
		assertNotSame(testBean1, testBean4);
		assertNotSame(testBean2, testBean3);
		assertNotSame(testBean2, testBean4);
		assertNotSame(testBean3, testBean4);

		assertTrue(factory.toString().indexOf("TestServiceLocator3") != -1);
	}
