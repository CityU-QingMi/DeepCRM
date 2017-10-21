	@Test
	public void testAutoProxyCreatorWithPackageVisibleMethod() {
		StaticApplicationContext sac = new StaticApplicationContext();
		sac.registerSingleton("testAutoProxyCreator", TestAutoProxyCreator.class);
		sac.registerSingleton("packageVisibleMethodToBeProxied", PackageVisibleMethod.class);
		sac.refresh();

		TestAutoProxyCreator tapc = (TestAutoProxyCreator) sac.getBean("testAutoProxyCreator");
		tapc.testInterceptor.nrOfInvocations = 0;

		PackageVisibleMethod tb = (PackageVisibleMethod) sac.getBean("packageVisibleMethodToBeProxied");
		assertTrue(AopUtils.isCglibProxy(tb));
		assertEquals(0, tapc.testInterceptor.nrOfInvocations);
		tb.doSomething();
		assertEquals(1, tapc.testInterceptor.nrOfInvocations);
	}
