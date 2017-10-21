	@Test
	public void testAutoProxyCreatorWithFactoryBeanAndProxyFactoryBeanOnly() {
		StaticApplicationContext sac = new StaticApplicationContext();

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("proxyObject", "false");
		sac.registerSingleton("testAutoProxyCreator", TestAutoProxyCreator.class, pvs);

		pvs = new MutablePropertyValues();
		pvs.add("singleton", "false");
		sac.registerSingleton("prototypeFactoryToBeProxied", DummyFactory.class, pvs);

		sac.refresh();

		TestAutoProxyCreator tapc = (TestAutoProxyCreator) sac.getBean("testAutoProxyCreator");
		tapc.testInterceptor.nrOfInvocations = 0;

		FactoryBean<?> prototypeFactory = (FactoryBean<?>) sac.getBean("&prototypeFactoryToBeProxied");
		assertTrue(AopUtils.isCglibProxy(prototypeFactory));
		TestBean tb = (TestBean) sac.getBean("prototypeFactoryToBeProxied");
		assertFalse(AopUtils.isCglibProxy(tb));

		assertEquals(2, tapc.testInterceptor.nrOfInvocations);
		tb.getAge();
		assertEquals(2, tapc.testInterceptor.nrOfInvocations);
	}
