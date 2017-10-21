	@Test
	public void testBeanNameAutoProxyCreatorWithFactoryBeanProxy() {
		StaticApplicationContext sac = new StaticApplicationContext();
		sac.registerSingleton("testInterceptor", TestInterceptor.class);

		RootBeanDefinition proxyCreator = new RootBeanDefinition(BeanNameAutoProxyCreator.class);
		proxyCreator.getPropertyValues().add("interceptorNames", "testInterceptor");
		proxyCreator.getPropertyValues().add("beanNames", "singletonToBeProxied,&singletonFactoryToBeProxied");
		sac.getDefaultListableBeanFactory().registerBeanDefinition("beanNameAutoProxyCreator", proxyCreator);

		RootBeanDefinition bd = new RootBeanDefinition(TestBean.class);
		sac.getDefaultListableBeanFactory().registerBeanDefinition("singletonToBeProxied", bd);

		sac.registerSingleton("singletonFactoryToBeProxied", DummyFactory.class);

		sac.refresh();

		ITestBean singletonToBeProxied = (ITestBean) sac.getBean("singletonToBeProxied");
		assertTrue(Proxy.isProxyClass(singletonToBeProxied.getClass()));

		TestInterceptor ti = (TestInterceptor) sac.getBean("testInterceptor");
		int initialNr = ti.nrOfInvocations;
		singletonToBeProxied.getName();
		assertEquals(initialNr + 1, ti.nrOfInvocations);

		FactoryBean<?> factory = (FactoryBean<?>) sac.getBean("&singletonFactoryToBeProxied");
		assertTrue(Proxy.isProxyClass(factory.getClass()));
		TestBean tb = (TestBean) sac.getBean("singletonFactoryToBeProxied");
		assertFalse(AopUtils.isAopProxy(tb));
		assertEquals(initialNr + 3, ti.nrOfInvocations);
		tb.getAge();
		assertEquals(initialNr + 3, ti.nrOfInvocations);
	}
