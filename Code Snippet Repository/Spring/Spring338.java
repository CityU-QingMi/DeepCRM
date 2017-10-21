	@Test
	public void testProxiedUserInterfacesWithSingleInterface() {
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(new TestBean());
		pf.addInterface(ITestBean.class);
		Object proxy = pf.getProxy();
		Class<?>[] userInterfaces = AopProxyUtils.proxiedUserInterfaces(proxy);
		assertEquals(1, userInterfaces.length);
		assertEquals(ITestBean.class, userInterfaces[0]);
	}
