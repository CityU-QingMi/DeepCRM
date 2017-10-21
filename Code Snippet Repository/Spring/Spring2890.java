	@Test
	public void testAutoProxyCreatorWithFactoryBeanAndProxyObjectOnly() {
		StaticApplicationContext sac = new StaticApplicationContext();

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("proxyFactoryBean", "false");
		sac.registerSingleton("testAutoProxyCreator", TestAutoProxyCreator.class, pvs);

		sac.registerSingleton("singletonFactoryToBeProxied", DummyFactory.class);

		sac.refresh();

		TestAutoProxyCreator tapc = (TestAutoProxyCreator) sac.getBean("testAutoProxyCreator");
		tapc.testInterceptor.nrOfInvocations = 0;

		FactoryBean<?> factory = (FactoryBean<?>) sac.getBean("&singletonFactoryToBeProxied");
		assertFalse(AopUtils.isAopProxy(factory));

		TestBean tb = (TestBean) sac.getBean("singletonFactoryToBeProxied");
		assertTrue(AopUtils.isCglibProxy(tb));
		assertEquals(0, tapc.testInterceptor.nrOfInvocations);
		tb.getAge();
		assertEquals(1, tapc.testInterceptor.nrOfInvocations);

		TestBean tb2 = (TestBean) sac.getBean("singletonFactoryToBeProxied");
		assertSame(tb, tb2);
		assertEquals(1, tapc.testInterceptor.nrOfInvocations);
		tb2.getAge();
		assertEquals(2, tapc.testInterceptor.nrOfInvocations);
	}
