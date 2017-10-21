	@Test
	public void testTargetClassProxiesCanBeOrderedThroughAnnotations() {
		ProxyFactory pf1 = new ProxyFactory(new A());
		pf1.setProxyTargetClass(true);
		ProxyFactory pf2 = new ProxyFactory(new B());
		pf2.setProxyTargetClass(true);
		Object proxy1 = pf1.getProxy();
		Object proxy2 = pf2.getProxy();
		List<Object> list = new ArrayList<>(2);
		list.add(proxy1);
		list.add(proxy2);
		AnnotationAwareOrderComparator.sort(list);
		assertSame(proxy2, list.get(0));
		assertSame(proxy1, list.get(1));
	}
