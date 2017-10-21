	@Test
	public void testAutoProxyCreatorWithFactoryBean() {
		StaticApplicationContext sac = new StaticApplicationContext();
		sac.registerSingleton("testAutoProxyCreator", TestAutoProxyCreator.class);
		sac.registerSingleton("singletonFactoryToBeProxied", DummyFactory.class);
		sac.refresh();

		TestAutoProxyCreator tapc = (TestAutoProxyCreator) sac.getBean("testAutoProxyCreator");
		tapc.testInterceptor.nrOfInvocations = 0;

		FactoryBean<?> factory = (FactoryBean<?>) sac.getBean("&singletonFactoryToBeProxied");
		assertTrue(AopUtils.isCglibProxy(factory));

		TestBean tb = (TestBean) sac.getBean("singletonFactoryToBeProxied");
		assertTrue(AopUtils.isCglibProxy(tb));
		assertEquals(2, tapc.testInterceptor.nrOfInvocations);
		tb.getAge();
		assertEquals(3, tapc.testInterceptor.nrOfInvocations);
	}
