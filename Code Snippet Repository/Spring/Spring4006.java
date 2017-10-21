	@Test
	public void testAopJdkProxy() throws Exception {
		ModelMap map = new ModelMap();
		ProxyFactory factory = new ProxyFactory();
		Map<?, ?> target = new HashMap<>();
		factory.setTarget(target);
		factory.addInterface(Map.class);
		Object proxy = factory.getProxy();
		map.addAttribute(proxy);
		assertSame(proxy, map.get("map"));
	}
