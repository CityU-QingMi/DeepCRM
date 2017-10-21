	@Test
	public void testCustomAutoProxyCreator() {
		StaticApplicationContext sac = new StaticApplicationContext();
		sac.registerSingleton("testAutoProxyCreator", TestAutoProxyCreator.class);
		sac.registerSingleton("noInterfaces", NoInterfaces.class);
		sac.registerSingleton("containerCallbackInterfacesOnly", ContainerCallbackInterfacesOnly.class);
		sac.registerSingleton("singletonNoInterceptor", TestBean.class);
		sac.registerSingleton("singletonToBeProxied", TestBean.class);
		sac.registerPrototype("prototypeToBeProxied", TestBean.class);
		sac.refresh();

		MessageSource messageSource = (MessageSource) sac.getBean("messageSource");
		NoInterfaces noInterfaces = (NoInterfaces) sac.getBean("noInterfaces");
		ContainerCallbackInterfacesOnly containerCallbackInterfacesOnly =
				(ContainerCallbackInterfacesOnly) sac.getBean("containerCallbackInterfacesOnly");
		ITestBean singletonNoInterceptor = (ITestBean) sac.getBean("singletonNoInterceptor");
		ITestBean singletonToBeProxied = (ITestBean) sac.getBean("singletonToBeProxied");
		ITestBean prototypeToBeProxied = (ITestBean) sac.getBean("prototypeToBeProxied");
		assertFalse(AopUtils.isCglibProxy(messageSource));
		assertTrue(AopUtils.isCglibProxy(noInterfaces));
		assertTrue(AopUtils.isCglibProxy(containerCallbackInterfacesOnly));
		assertTrue(AopUtils.isCglibProxy(singletonNoInterceptor));
		assertTrue(AopUtils.isCglibProxy(singletonToBeProxied));
		assertTrue(AopUtils.isCglibProxy(prototypeToBeProxied));

		TestAutoProxyCreator tapc = (TestAutoProxyCreator) sac.getBean("testAutoProxyCreator");
		assertEquals(0, tapc.testInterceptor.nrOfInvocations);
		singletonNoInterceptor.getName();
		assertEquals(0, tapc.testInterceptor.nrOfInvocations);
		singletonToBeProxied.getAge();
		assertEquals(1, tapc.testInterceptor.nrOfInvocations);
		prototypeToBeProxied.getSpouse();
		assertEquals(2, tapc.testInterceptor.nrOfInvocations);
	}
