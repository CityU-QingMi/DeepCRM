	@Test
	public void testAutoProxyCreatorWithFactoryBeanAndPrototype() {
		StaticApplicationContext sac = new StaticApplicationContext();
		sac.registerSingleton("testAutoProxyCreator", TestAutoProxyCreator.class);

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("singleton", "false");
		sac.registerSingleton("prototypeFactoryToBeProxied", DummyFactory.class, pvs);

		sac.refresh();

		TestAutoProxyCreator tapc = (TestAutoProxyCreator) sac.getBean("testAutoProxyCreator");
		tapc.testInterceptor.nrOfInvocations = 0;

		FactoryBean<?> prototypeFactory = (FactoryBean<?>) sac.getBean("&prototypeFactoryToBeProxied");
		assertTrue(AopUtils.isCglibProxy(prototypeFactory));
		TestBean tb = (TestBean) sac.getBean("prototypeFactoryToBeProxied");
		assertTrue(AopUtils.isCglibProxy(tb));

		assertEquals(2, tapc.testInterceptor.nrOfInvocations);
		tb.getAge();
		assertEquals(3, tapc.testInterceptor.nrOfInvocations);
	}
