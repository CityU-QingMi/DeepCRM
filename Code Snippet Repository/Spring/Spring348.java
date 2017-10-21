	@Test
	public void testInterfaceProxiesCanBeOrderedThroughAnnotations() {
		Object proxy1 = new ProxyFactory(new A()).getProxy();
		Object proxy2 = new ProxyFactory(new B()).getProxy();
		List<Object> list = new ArrayList<>(2);
		list.add(proxy1);
		list.add(proxy2);
		AnnotationAwareOrderComparator.sort(list);
		assertSame(proxy2, list.get(0));
		assertSame(proxy1, list.get(1));
	}
