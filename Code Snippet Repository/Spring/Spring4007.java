	@Test
	public void testAopJdkProxyWithMultipleInterfaces() throws Exception {
		ModelMap map = new ModelMap();
		Map<?, ?> target = new HashMap<>();
		ProxyFactory factory = new ProxyFactory();
		factory.setTarget(target);
		factory.addInterface(Serializable.class);
		factory.addInterface(Cloneable.class);
		factory.addInterface(Comparable.class);
		factory.addInterface(Map.class);
		Object proxy = factory.getProxy();
		map.addAttribute(proxy);
		assertSame(proxy, map.get("map"));
	}
