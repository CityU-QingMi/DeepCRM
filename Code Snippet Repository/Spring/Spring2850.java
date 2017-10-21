	@Test
	public void testProxyNotWrappedIfIncompatible() {
		FooBar bean = new FooBar();
		ProxyCreatorSupport as = new ProxyCreatorSupport();
		as.setInterfaces(Foo.class);
		as.setTarget(bean);

		Foo proxy = (Foo) createProxy(as);
		assertSame("Target should be returned when return types are incompatible", bean, proxy.getBarThis());
		assertSame("Proxy should be returned when return types are compatible", proxy, proxy.getFooThis());
	}
